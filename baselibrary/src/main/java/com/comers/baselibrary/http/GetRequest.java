package com.comers.baselibrary.http;

import android.text.TextUtils;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by Comers on 2017/10/24.
 */

public class GetRequest extends BaseRequest<GetRequest> {
    public GetRequest(String url) {
        super(url);
    }

    public GetRequest add(String key, Object value) {
        if (TextUtils.isEmpty(key)) {
            return this;
        }
        if (value == null) {
            mObjectMaps.put(key, "");
        } else {
            mObjectMaps.put(key, value);
        }
        return this;
    }

    public <T> void execute(BaseCallBack<T> callBack) {
        if (TextUtils.isEmpty(mURI)) {
            //提示输入URL
            return;
        }
        //TODO 添加公共头部
        String url = getFixUrl(mURI);
        final Request request = new Request.Builder()
                .url(url)
//                .addHeader("Cookie", "token=" + SharedUtils.get(ConstantsPool.TOKEN, ""))
                .addHeader("Cookie", "app=android")
//                .addHeader("Cookie", "version=" + UIUtils.getVersionCode())
                .build();
        perform(request, callBack);
    }
    public <T> void execute(Class<T> tClass,BaseCallBack<T> callBack){}
    public <T> void executeSync(BaseCallBack<T> callBack) {
        if (TextUtils.isEmpty(mURI)) {
            //提示输入URL
            return;
        }
        String url = getFixUrl(mURI);
        final Request request = new Request.Builder()
                .url(url)
//                .addHeader("Cookie", "token=" + SharedUtils.get(ConstantsPool.TOKEN, ""))
                .addHeader("Cookie", "app=android")
//                .addHeader("Cookie", "version=" + UIUtils.getVersionCode())
                .build();
        performSync(request, callBack);
    }

    private String getFixUrl(String url) {
        String argument = "";
        for (Map.Entry<String, Object> param : mObjectMaps.entrySet()) {
            if (param.getKey() != null) {
                if (TextUtils.isEmpty(argument)) {
                    argument = param.getKey() + "=" + param.getValue();
                } else {
                    if (null!=param.getKey()) {
                        argument = argument + "&" + param.getKey() + "=" + param.getValue();
                    } else {
                        argument = argument + "&" + param.getKey() + "=" +"";
                    }
                }
            }
        }
        return url+"?"+argument;
    }
}
