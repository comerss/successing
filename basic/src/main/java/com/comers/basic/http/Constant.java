package com.comers.basic.http;

/**
 * Created by lq on 2017/2/19.
 * 作者：栗启
 * 作用：接口
 */
public class Constant {
    /*
    * 上线流程
    * 1.更改  IS_DEBUG=false; / AppLike ---> Bugly  线上以及测试账号不同
    * 2.更改  host
    * 3.确认版本号  以及Code
    * 4.确认 release 包  ，影响  applicationId
    * */
    //------------------------------------接口线上线下配置----------------------------------------------------------------
    //是否上线  影响   友盟、bugly  账号
    public static final boolean IS_DEBUG = true;
    //线上
//    public static final String Host = "https://app.liangyibang.com/";//默认线上
    //测试
//    public static final String Host = "https://app2.liangyibang.com/";
    //本地
    public static final String Host = "http://10.0.0.57:8082/";
    //-----------------------------华丽分割线----------------------------------------------------------------------------
    //登录
    public static final String LOGIN="bd/login/pass";
    //处方列表
    public static final String RECIPEL_LIST="bd/recipel/bdlist";
    //待录方
    public static final String UN_FINISHED = "/electron/recipel/detail";


}
