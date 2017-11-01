package com.comers.basic.base;

/**
 * Created by human on 2017/5/27.
 * 返回数据的基类
 */
public class HttpResult<T> {
    public int code;
    public String msg;
    public T data;
    public boolean success;
    public void getType(){

    }
}
