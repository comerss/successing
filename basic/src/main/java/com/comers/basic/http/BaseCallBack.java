package com.comers.basic.http;

import java.lang.reflect.Type;

/**
 * Created by Comers on 2017/10/25.
 */

public abstract class BaseCallBack<T> {
    public abstract void onSuccess(HttpResult<T> sResult);
    public abstract void onError(String msg);
    public Type getType() {//获取需要解析的泛型T类型
        return  new ParameterizedTypeImpl(HttpResult.class, new Type[]{TakeTypeUtils.findNeedClass(getClass())});
    }

    public Type getRawType() {//获取需要解析的泛型T raw类型
        return TakeTypeUtils.findRawType(getClass());
    }
}
