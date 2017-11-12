package com.comers.baselibrary.http;

import com.comers.basic.base.LoadingDialog;
import com.comers.basic.utils.ConstantsPool;
import com.comers.basic.utils.SharedUtils;
import com.comers.basic.utils.ToastUtils;
import com.comers.basic.utils.UIUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Comers on 2017/10/24.
 */

public class BaseRequest<R extends BaseRequest> {
    String mURI = "";
    Map<String, Object> mObjectMaps = new HashMap<>();
    Gson mGson = new Gson();
    boolean mShowDialog = true;
    LoadingDialog mLoadingDialog;

    public BaseRequest(String url) {
        mURI = url;
        if (HttpHelper.mContext != null)
            mLoadingDialog = new LoadingDialog(HttpHelper.mContext);
        mObjectMaps.clear();
        mObjectMaps.put("token", SharedUtils.get(ConstantsPool.TOKEN,""));
        mObjectMaps.put("app", "android");
        mObjectMaps.put("version", UIUtils.getVersionCode());
    }

    public R params(Map<String, Object> params) {
        if (params == null || params.isEmpty())
            return (R) this;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            if (param.getKey() != null) {
                if (param.getValue() != null) {
                    mObjectMaps.put(param.getKey(), param.getValue());
                } else {
                    mObjectMaps.put(param.getKey(), "");
                }
            }
        }
        return (R) this;
    }

    public R showDialog(boolean showDialog) {
        mShowDialog = showDialog;
        return (R) this;
    }

    //为了方便链式调用
    public R connectTimeout(long connectTimeout) {
        HttpHelper.getInstance().connectTimeout(connectTimeout);
        return (R) this;
    }

    public R writeTimeout(long writeTimeout) {
        HttpHelper.getInstance().connectTimeout(writeTimeout);
        return (R) this;
    }

    public R readTimeout(long readTimeout) {
        HttpHelper.getInstance().connectTimeout(readTimeout);
        return (R) this;
    }

    public <T> void perform(final Request request, final BaseCallBack<T> callBack) {
        if (mLoadingDialog != null) {
            try {
                if (mShowDialog)
                    mLoadingDialog.show();
            } catch (Exception e) {

            }
        }
        HttpHelper.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, final IOException e) {
                Platform.execute(new Runnable() {
                    @Override
                    public void run() {
                        doError(callBack, e);
                        if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
                            try {
                                mLoadingDialog.dismiss();
                            }catch (Exception e1){

                            }
                        }
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!call.isCanceled()) {
                    final String json = response.body().string();
                    Platform.execute(new Runnable() {
                        @Override
                        public void run() {
                            HttpResult<T> result=(HttpResult<T>) mGson.fromJson(json, callBack.getType());
                            if(result!=null&&result.code!=0){
                                ToastUtils.showToast(result.msg);
                            }else{
                                callBack.onSuccess(result);
                            }
                            if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
                                try {
                                    mLoadingDialog.dismiss();
                                }catch (Exception e1){

                                }
                            }
                        }
                    });
                }
            }
        });
    }

    public <T> void performSync(final Request request, final BaseCallBack<T> callBack) {
        if (mLoadingDialog != null) {
            try {
                if (mShowDialog)
                    mLoadingDialog.show();
            } catch (Exception e) {

            }
        }
        Response response = null;
        String json = "";
        try {
            response = HttpHelper.getClient().newCall(request).execute();
        } catch (Exception e) {
            if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
                try {
                    mLoadingDialog.dismiss();
                }catch (Exception e1){

                }
            }
            e.printStackTrace();
            doError(callBack, e);
        }
        if (response.isSuccessful()) {
            try {
                json = response.body().string();
            } catch (IOException e) {
                callBack.onError(e.getMessage());
                if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
                    try {
                        mLoadingDialog.dismiss();
                    }catch (Exception e1){

                    }
                }
                e.printStackTrace();
            }
            callBack.onSuccess((HttpResult<T>) mGson.fromJson(json, callBack.getType()));
        } else {
            callBack.onError("请求失败，请稍后重试！");
        }
        if(mLoadingDialog!=null&&mLoadingDialog.isShowing()){
            try {
                mLoadingDialog.dismiss();
            }catch (Exception e1){

            }
        }
    }
    //网络请求失败的情况处理
    private <T> void doError(BaseCallBack<T> callBack, Exception e) {
        if (e instanceof SocketTimeoutException) {
            callBack.onError("网络连接超时,请检查网络！");
            ToastUtils.showToast("网络连接超时,请检查网络！");
        } else if (e instanceof SocketException) {
            if (e instanceof ConnectException) {
                callBack.onError("网络未连接，请检查网络！");
                ToastUtils.showToast("网络未连接，请检查网络！");
            } else {
                callBack.onError("网络错误，请检查网络！");
            }
        } else {
            callBack.onError("服务器无响应，请稍后重试！");
            ToastUtils.showToast("服务器无响应，请稍后重试！");
        }
    }

}
