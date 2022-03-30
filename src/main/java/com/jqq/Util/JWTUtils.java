package com.jqq.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    /*
    * 生成token header.payload.signature
    * */
    private static final String SIGN ="!!!";
    public static String getToken(Map<String,String> map){

        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MONTH,60*60*24*7);//默认七天过期
        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k, v);
        });

        String token = JWT.create()
                .sign(Algorithm.HMAC256(SIGN));//签名
        return token;

    }
    /*验证Token的合法性*/
    public static void verify(String token){
        JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);

    }
    /*获取Token的信息*/
    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
        return verify;
    }
}
