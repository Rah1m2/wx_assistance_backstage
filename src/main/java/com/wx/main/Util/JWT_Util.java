package com.wx.main.Util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWT_Util {

    private static String SECRET = "michael";  // 秘钥（公钥）

    /**
     * 生成token
     * @param user_name
     * @param user_password
     * @return
     * @expireTime
     */
    public static String createToken(String user_name, String user_password){
        Calendar expireTime = Calendar.getInstance();
        expireTime.add(Calendar.SECOND,1800);
        // 签发时间
        Date iatDate = new Date();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        String token = null; // 加密
        System.out.println("token >>id" + user_name);
        token = JWT.create()
                .withHeader(map)
                .withClaim("user_name", user_name)
                .withClaim("user_password", user_password)
                .withExpiresAt(expireTime.getTime())  // 设置过期的日期
                .withIssuedAt(iatDate)        // 签发时间
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 解密
     * @param token
     * @return
     * @throws Exception
     */
    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = null;
        try {
            verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DecodedJWT jwt = null;

        try {
            jwt = verifier.verify(token);  // 核实token
        } catch (TokenExpiredException te) {
            System.out.println("token expired.");
            throw new TokenExpiredException("token expired.");
       }
        return jwt.getClaims();  // 这里解密jwt得到的是一个map
    }


}