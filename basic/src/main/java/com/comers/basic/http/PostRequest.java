package com.comers.basic.http;

import android.text.TextUtils;

import com.liangyibang.market.utils.ConstantsPool;
import com.liangyibang.market.utils.SharedUtils;
import com.liangyibang.market.utils.UIUtils;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Comers on 2017/10/24.
 */

public class PostRequest extends BaseRequest<PostRequest> {
    public PostRequest(String url){
        super(url);
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
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        final Request request = new Request.Builder()
                .url(mURI)
                .addHeader("Cookie", "token=" + SharedUtils.INSTANCE.get(ConstantsPool.TOKEN, ""))
                .addHeader("Cookie", "app=android")
                .addHeader("Cookie", "version=" + UIUtils.getVersionCode())
                .post(body)
                .build();
        perform(request, callBack);
    }
    public <T> void executeSync(BaseCallBack<T> callBack) {
        if (TextUtils.isEmpty(mURI)) {
            //提示输入URL
            return;
        }
        String json=JsonParseHelper.parse(mObjectMaps);
        RequestBody body= RequestBody.create(MediaType.parse("application/json; charset=utf-8"),json);
        final Request request = new Request.Builder()
                .url(mURI)
                .addHeader("Cookie", "token=" + SharedUtils.INSTANCE.get(ConstantsPool.TOKEN, ""))
                .addHeader("Cookie", "app=android")
                .addHeader("Cookie", "version=" + UIUtils.getVersionCode())
                .post(body)
                .build();
        performSync(request, callBack);
    }

}
