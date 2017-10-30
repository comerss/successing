package com.comers.basic.http;

import android.text.TextUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Comers on 2017/10/24.
 */

public class FormRequest extends BaseRequest<FormRequest> {

    private final MultipartBody.Builder mBuilder;

    public FormRequest(String url) {
        super(url);
        mBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);
    }

    public FormRequest add(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            return this;
        }
        if (TextUtils.isEmpty(value)) {
            mBuilder.addFormDataPart(key, "");
        } else {
            mBuilder.addFormDataPart(key, value);
        }
        return this;
    }

    public FormRequest addFile(String filePath) {
        if (TextUtils.isEmpty(filePath))
            return this;
        mBuilder.addFormDataPart("files", "files", RequestBody.create(MediaType.parse("image/png"), new File(filePath)));
        return this;
    }

    public FormRequest addFiles(List<String> files) {
        if (files == null || files.size() == 0) {
            return this;
        }
        for (String str : files) {
            mBuilder.addFormDataPart("files", "files", RequestBody.create(MediaType.parse("image/png"), new File(str)));
        }
        return this;
    }

    public <T> void execute(BaseCallBack<T> callBack) {
        if(TextUtils.isEmpty(mURI)){
            //提示输入url
            return;
        }
        for(Map.Entry<String,Object> param:mObjectMaps.entrySet()){
            if(param.getKey()!=null){
                if(param.getValue()!=null){
                    mBuilder.addFormDataPart(param.getKey(),param.getValue().toString());
                }else{
                    mBuilder.addFormDataPart(param.getKey(),"");
                }
            }
        }
        //TODO 添加公共头部
        final Request request = new Request.Builder()
                .url(mURI)
                .post(mBuilder.build())
                .build();
        perform(request, callBack);
    }
}
