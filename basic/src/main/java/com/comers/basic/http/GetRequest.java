package com.comers.basic.http;

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
        }
        //TODO 添加公共头部
        String url = getFixUrl(mURI);
        final Request request = new Request.Builder()
                .url(url)
                .build();
        perform(request, callBack);
    }

    private String getFixUrl(String url) {
        for (Map.Entry<String, Object> param : mObjectMaps.entrySet()) {
            if (param.getKey() != null) {
                if (null != param.getValue()) {
                    url = url + "&" + param.getKey() + "=" + param.getValue();
                } else {
                    url = url + "&" + param.getKey() + "=" + "";
                }
            }
        }
        return url;
    }
}
