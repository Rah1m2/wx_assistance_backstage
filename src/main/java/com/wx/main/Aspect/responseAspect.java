package com.wx.main.Aspect;

import com.alibaba.fastjson.JSONObject;
import com.wx.main.POJO.Posting;
import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@ControllerAdvice
public class responseAspect implements ResponseBodyAdvice<Object> {

    private RedisTemplate redisTemplate;

    @Autowired
    public responseAspect(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        //定位要加入推荐信息的controller
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) serverHttpRequest).getServletRequest();
        String URI = httpServletRequest.getRequestURI();
        if (!URI.equals("/wx/pst/reqPostings"))
            return body;

        ServletServerHttpResponse responseTemp = (ServletServerHttpResponse) serverHttpResponse;
        HttpServletResponse response = responseTemp.getServletResponse();

        JSONObject jsonObject;
        try {
            jsonObject = com.alibaba.fastjson.JSONObject.parseObject(String.valueOf(response));
        } catch (Exception e) {
            System.out.println("---response passed---");
            return body;
        }

        List<Posting> articleList = (List<Posting>) jsonObject.get("articleList");
//        articleList.add()
        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
        //TODO 读取redis数据

        return body;
    }
}
