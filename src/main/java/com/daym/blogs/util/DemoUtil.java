package com.daym.blogs.util;

import java.lang.reflect.ParameterizedType;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Date: 2024/4/18
 * Time: 13:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class DemoUtil {
    public static <T> Class<T> getTClass(Object instance) {
        Class<T> tClass = (Class<T>)((ParameterizedType)instance.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return tClass;
    }
}
