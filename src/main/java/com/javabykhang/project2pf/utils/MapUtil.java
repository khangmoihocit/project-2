package com.javabykhang.project2pf.utils;

import java.util.Map;

public class MapUtil {
    //vì value trong map có type là object nên cần chuyển về dạng long, int,... rồi gắn cho builder
    public static <T> T getObject(Map<String, Object> params, String key, Class<T> tClass){
        Object obj = params.getOrDefault(key, null);
        if (obj != null){
            if (tClass.getTypeName().equals("java.lang.Long")){
                obj = obj != null ? Long.parseLong(obj.toString()) : null;
            }
            else if (tClass.getTypeName().equals("java.lang.Integer")){
                obj = obj != null ? Integer.parseInt(obj.toString()) : null;
            }
            else if (tClass.getTypeName().equals("java.lang.String")){
                obj = obj.toString();
            }
            return tClass.cast(obj);
        }
        return null;
    }
}
