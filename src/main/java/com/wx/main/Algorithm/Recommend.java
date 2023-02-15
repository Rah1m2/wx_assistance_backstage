package com.wx.main.Algorithm;

import com.wx.main.Util.RedisTemplate_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.*;
import java.util.stream.Collectors;

public class Recommend {

    private RedisTemplate redisTemplate;

    @Autowired
    public Recommend(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void getUserVectorMatrix() {
        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
        Set<String> querySet = (Set<String>) redisTemplate_util.queryKey("db_graduate_design:user_vector:*");
//        querySet.stream().collect(Collectors.groupingBy())
        List<Map<String, String>> queryList = new ArrayList<>();
        Map<String, String> tempMap = new HashMap<>();
        String[] splitRedisKey;

        Iterator<Map<String, String>> listIt = queryList.iterator();
        for (String aQuerySet : querySet) {
            splitRedisKey = aQuerySet.split(":");
            tempMap.put("user_openid", splitRedisKey[2]);
            tempMap.put("article_id", splitRedisKey[3]);
            tempMap.put("vector", splitRedisKey[4]);
            queryList.add(tempMap);
        }

        Map<String, List<Map<String, String>>> groupbyMap = queryList.stream().collect(Collectors.groupingBy(e -> e.get("user_openid")));

    }
}
