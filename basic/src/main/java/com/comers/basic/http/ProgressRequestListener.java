package com.comers.basic.http;

/**
 * Created by Comers on 2017/10/30.
 */

interface ProgressRequestListener {
    void onRequestProgress(long bytesWritten, long contentLength, boolean done);
}
