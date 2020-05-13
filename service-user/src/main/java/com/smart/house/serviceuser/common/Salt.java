package com.smart.house.serviceuser.common;

import com.google.common.base.Throwables;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * MD5加密算法
 */
public class Salt {
    private static  final HashFunction FUNCTION= Hashing.md5();
    private static final String salt="salt";       //盐值
    public static String encryPassword(String password){
        HashCode hashCode=FUNCTION.hashString(password+salt, Charset.forName("UTF-8"));
        return String.valueOf(hashCode);   //返回hash码作为加密后的密码
    }


    public static String hashString(String input){
        HashCode code = null;
        try {
            code = FUNCTION.hashBytes(input.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            Throwables.propagate(e);
        }
        return code.toString();
    }
}
