package com.comers.basic.http;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Comers on 2017/10/24.
 */

public class HttpHelper {

    private static HttpHelper mSingleton;
    private static long CONNECT_TIME_OUT = 30;
    private static long WRITE_TIME_OUT = 60;
    private static long READ_TIME_OUT = 60;
    private volatile static OkHttpClient mOkHttpClient;

    public static HttpHelper getInstance() {
        if (mSingleton == null) {
            synchronized (HttpHelper.class) {
                if (mSingleton == null) {
                    mSingleton = new HttpHelper();
                }
            }
        }
        return mSingleton;
    }

    public static FormRequest doForm() {
        return new FormRequest(BaseRequest.Type.FORM);
    }
    public static PostRequest doPost() {
        return new PostRequest(BaseRequest.Type.POST);
    }
    public static GetRequest doGet() {
        return new GetRequest(BaseRequest.Type.GET);
    }

    public static OkHttpClient getClient() {
        if (mOkHttpClient == null) {
            synchronized (HttpHelper.class) {
                if (mOkHttpClient == null) {
                    HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
                    mOkHttpClient = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)
                            .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)
                            .addNetworkInterceptor(logInterceptor)
                            .build();
                }
            }
        }
        return mOkHttpClient;
    }

    public HttpHelper connectTimeout(long connectTimeout) {
        CONNECT_TIME_OUT = connectTimeout;
        return this;
    }

    public HttpHelper writeTimeout(long writeTimeout) {
        WRITE_TIME_OUT = writeTimeout;
        return this;
    }

    public HttpHelper readTimeout(long readTimeout) {
        READ_TIME_OUT = readTimeout;
        return this;
    }
    public static class HttpLogger implements HttpLoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            Log.d("HttpLogInfo", message);
        }
    }
}
