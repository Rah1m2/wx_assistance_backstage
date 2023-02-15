package com.wx.main.Aspect;

import com.wx.main.Annotation.Limit;
import com.wx.main.VO.ResponseData;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.atomic.AtomicInteger;


@Aspect
@Configuration
public class LimitAspect {

    private long interval = 60;  //限制刷新时间为60秒
    private int maxCount = 5;    //默认最大流量为5
    private AtomicInteger atomicInteger = new AtomicInteger(0);  //用于记录流量
    private long startTime = System.currentTimeMillis();
    private RedisTemplate redisTemplate;

//    @Autowired
//    public LimitAspect(RedisTemplate redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }

    @Around(value = "@annotation(limit)")
    public Object around(ProceedingJoinPoint joinPoint, Limit limit) throws Throwable {
        Object proceed;

        //设置流量
        maxCount = Math.toIntExact(limit.limit());

        //前置通知
        System.out.println("executing aspect...");
//        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
//        redisTemplate_util.set("db_graduate_design:test:1", "hello");

        //计数器+1
        atomicInteger.addAndGet(1);
        long time = System.currentTimeMillis();

        //如果超过时间则直接重新开始计算
        if (System.currentTimeMillis()-startTime >interval*1000) {
            startTime = System.currentTimeMillis();
            atomicInteger.set(1);
        }

        //如果原子计数器超过最大限制则拒绝访问
        if (atomicInteger.get() > maxCount) {
            return ResponseData.forbidden();
        }

        //运行业务方法
        proceed = joinPoint.proceed();

        //后置通知
        System.out.println("after aspect.");

        //返回业务方法运行结果
        return proceed;
    }

}
