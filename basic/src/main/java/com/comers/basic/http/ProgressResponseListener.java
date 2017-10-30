package com.comers.basic.http;

/**
 * Created by Comers on 2017/10/30.
 */

public interface ProgressResponseListener {

    void onResponseProgress(long totalBytesRead, long contentLength, boolean done);
}
