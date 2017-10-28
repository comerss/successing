package com.comers.basic.http;

import android.text.TextUtils;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Comers on 2017/10/24.
 */

public class PostRequest extends BaseRequest {
    public PostRequest(Type type){
        super(type);
    }
    public PostRequest add(String key, Object value) {
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
        String json=JsonParseHelper.parse(mObjectMaps);
        //TODO 添加公共头部

        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        final Request request = new Request.Builder()
                .url(mURI)
                .post(body)
                .build();
        perform(request, callBack);
    }

}
