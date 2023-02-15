package com.wx.main.Aspect;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.Claim;
import com.wx.main.Algorithm.Recommend;
import com.wx.main.Annotation.Calculate;
import com.wx.main.Util.JWT_Util;
import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.Util.Url_Util;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect
@Configuration
public class AlgorithmAspect {
    private RedisTemplate redisTemplate;
    private HttpServletRequest request;

    public AlgorithmAspect() {
    }

    @Autowired
    public AlgorithmAspect(RedisTemplate redisTemplate, HttpServletRequest request) {
        this.redisTemplate = redisTemplate;
        this.request = request;
    }


    @Around(value = "@annotation(calculate)")
    public Object around(ProceedingJoinPoint joinPoint, Calculate calculate) throws Throwable {
        Object proceed;

        //获取article_id
        Url_Util url_util = new Url_Util(request);
        Map<String, String> params_map = url_util.getParameters();
//        System.out.println("token:"+params_map.get("token"));
        String article_id = params_map.get("article_id");

        //获取请求头中token信息
        System.out.println(request.getHeader("token"));
        String token = request.getHeader("token");
        if (token.equals("\"\"")) {
            //运行业务方法
            proceed = joinPoint.proceed();

            //后置通知
            System.out.println("after aspect.");

            //返回业务方法运行结果
            return proceed;
        }
        JSONObject token_obj = com.alibaba.fastjson.JSONObject.parseObject(request.getHeader("token"));

        //提取出user_openid
        System.out.println("token_obj:"+token_obj.get("userInfo"));
        Map<String, String> userMap = (Map<String, String>) token_obj.get("userInfo");
        System.out.println("userMap:"+userMap.get("user_openid"));
        String user_openid = userMap.get("user_openid");

        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
        Object redis_get_result = redisTemplate_util.get("db_graduate_design:user_vector:" + user_openid + ":" + article_id);
        int redis_set_param = 1;
        if (null == redis_get_result) {
            redisTemplate_util.set("db_graduate_design:user_vector:" + user_openid + ":" + article_id, redis_set_param);
        } else {
            redis_set_param = (int) redis_get_result + 1;
            redisTemplate_util.set("db_graduate_design:user_vector:" + user_openid + ":" + article_id, redis_set_param);
        }

        //运行业务方法
        proceed = joinPoint.proceed();

        //后置通知
        System.out.println("after aspect.");

        Recommend recommend = new Recommend(redisTemplate);
        recommend.getUserVectorMatrix();

        //返回业务方法运行结果
        return proceed;
    }


    private void getUrl(){
        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath() + "?" + request.getQueryString());
    }
}