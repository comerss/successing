package com.comers.basic.http;

/**
 * Created by Comers on 2017/10/25.
 */

public class HttpResult<T> {
    public String msg;
    public boolean success;
    public int code;
    public T data;

    @Override
    public String toString() {
        return "HttpResult{" +
                "msg='" + msg + '\'' +
                ", success=" + success +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
