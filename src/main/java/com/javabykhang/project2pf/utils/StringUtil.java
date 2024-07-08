package com.javabykhang.project2pf.utils;

public class StringUtil {
    public static boolean checkString(String s){
        boolean isCheck = false;
        if (s != null && !s.equals("")){
            isCheck = true;
        }
        return isCheck;
    }
}
