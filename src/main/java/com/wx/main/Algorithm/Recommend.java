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

    public List<Map<String, String>> getUserVectorMatrix(String current_user_openid) {

        //redis key的公共部分
        String baseKey = "db_graduate_design:user_vector:";
        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
        //redis查询所有用户的兴趣向量
        Set<String> querySet = (Set<String>) redisTemplate_util.queryKey(baseKey + "*");

        //用于存储查询出来的用户信息
        List<Map<String, String>> queryList = new ArrayList<>();
        //用于分割用户信息并写入list的临时map
        Map<String, String> tempMap = new HashMap<>();
        //将redis的key按照:分割以便存入map
        String[] splitRedisKey;
        //用户兴趣向量
        String vector;
        //将用户信息存入list，包含：user_openid、article_id、vector三部分
        for (String aQuerySet : querySet) {
            splitRedisKey = aQuerySet.split(":");
            vector = String.valueOf(redisTemplate_util.get(baseKey + splitRedisKey[2] + ":" + splitRedisKey[3]));
            tempMap.put("user_openid", splitRedisKey[2]);
            tempMap.put("article_id", splitRedisKey[3]);
            tempMap.put("vector", vector);
            queryList.add(tempMap);
            tempMap = new HashMap<>();
        }

        //将查询出来的用户兴趣向量按照user_openid进行分类，groupbyMap为groupby操作完成后的Map，key为user_openid，value为List
        Map<String, List<Map<String, String>>> groupbyMap = queryList.stream().collect(Collectors.groupingBy(e -> e.get("user_openid")));

        //用于存储用户向量的矩阵
        List<Map<String, String>> userVectorMatrix = new ArrayList<>();
        //用于向用户向量矩阵写入数据的map
        Map<String, String> matrixMap = new HashMap<>();

        //将当前用户提取出来放入用户矩阵0号索引下
        List<Map<String, String>> curUserList = groupbyMap.get(current_user_openid);
        groupbyMap.remove(current_user_openid);
        for (Map<String, String> stringStringMap : curUserList) {
            matrixMap.put(stringStringMap.get("article_id"), stringStringMap.get("vector"));
        }
        matrixMap.put("user_openid", current_user_openid);
        userVectorMatrix.add(matrixMap);

        //遍历groupbyMap依次将剩余用户写入用户矩阵
        Iterator<Map.Entry<String, List<Map<String, String>>>> entries = groupbyMap.entrySet().iterator();
        while (entries.hasNext()) {
            //获取下一个元素
            Map.Entry<String, List<Map<String, String>>> entry = entries.next();
            matrixMap = new HashMap<>();
            //遍历每个user_openid下的list
            for (Map<String, String> stringStringMap : entry.getValue()) {
                matrixMap.put(stringStringMap.get("article_id"), stringStringMap.get("vector"));
            }
            matrixMap.put("user_openid", entry.getKey());
            userVectorMatrix.add(matrixMap);
        }

        return userVectorMatrix;
    }
}
