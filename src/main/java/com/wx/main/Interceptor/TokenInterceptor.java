package com.wx.main.Interceptor;


import com.alibaba.fastjson.JSON;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.wx.main.Util.JWT_Util;
import com.wx.main.VO.ResponseData;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

//        System.out.println("test：计算器token前。");

        String token = httpServletRequest.getHeader("token");

        System.out.println(token);

        //如果是默认表示没有登录
        if ( token == null || token.equals("null") || token.equals("undefined")) {
            PrintWriter out = httpServletResponse.getWriter();
            ResponseData responseData = ResponseData.tokenExpired();
            out.write(JSON.toJSONString(responseData));
            return false;
        }


        Map<String, Claim> verifiedToken = null;

        try {
            verifiedToken = JWT_Util.verifyToken(token);
        } catch (TokenExpiredException te) {
            System.out.println(te.getMessage());
            PrintWriter out = httpServletResponse.getWriter();
            ResponseData responseData = ResponseData.tokenExpired();
            out.write(JSON.toJSONString(responseData));
            return false;
        }

        System.out.println("test：计算token后，进入执行链前。");

//        System.out.println("token打印："+verifiedToken);
//
//        Map<String, String[]> m = new HashMap<String, String[]>(httpServletRequest.getParameterMap());
//
//        m.put("user_openid", new String[] {String.valueOf(verifiedToken.get("user_name"))});

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

