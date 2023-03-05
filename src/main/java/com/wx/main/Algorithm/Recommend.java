package com.wx.main.Algorithm;

import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.VO.ResponseData;
import org.apache.wicket.util.value.CopyOnWriteValueMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.xml.ws.Response;
import java.util.*;
import java.util.stream.Collectors;

public class Recommend {

    private RedisTemplate redisTemplate;

    @Autowired
    public Recommend(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public ResponseData processRecommend(List<Map<String, String>> userVectorMatrix, String current_user_openid) {
        List<Map<String, String>> userCosineMatrix;
//        Map<String, String> currentUserMap = new HashMap<>();
//        currentUserMap.put("1", "5");
//        currentUserMap.put("2", "3");
//        currentUserMap.put("3", "4");
//        currentUserMap.put("4", "4");
//
//        Map<String, String> otherUserMap = new HashMap<>();
//        otherUserMap.put("1", "3");
//        otherUserMap.put("2", "1");
//        otherUserMap.put("3", "2");
//        otherUserMap.put("4", "3");
//        otherUserMap.put("5", "3");
//        //测试
//        calculateCosineSimilarity(currentUserMap, otherUserMap);

        //索引为0的是当前用户的兴趣向量map，用浅拷贝
        Map<String, String> currentUserMap = userVectorMatrix.get(0);
        //其他用户的兴趣向量map
        Map<String, String> otherUserMap;
        //当前用户的余弦相似度map
        Map<String, Object> currentUserCosineMap = new HashMap<>();
        //存入当前用户的user_openid
        currentUserCosineMap.put("current_user_openid",current_user_openid);
        //生成当前用户的余弦相似度map
        for(int i=1; i<userVectorMatrix.size(); i++) {
            otherUserMap = userVectorMatrix.get(i);
            currentUserCosineMap.put(otherUserMap.get("user_openid"), calculateCosineSimilarity(currentUserMap, otherUserMap));
        }

        //选出与当前用户余弦相似度最高的两个用户
        String maxCosineUserOpenid = "";
        double maxCosine = 0.0;
        String secondCosineUserOpenid = "";
        double secondMaxCosine = 0.0;
        //选出最大的
        for (String s : currentUserCosineMap.keySet()) {
            if (!s.equals("current_user_openid") && Double.parseDouble(currentUserCosineMap.get(s).toString()) > maxCosine) {
                maxCosine = Double.parseDouble(currentUserCosineMap.get(s).toString());
                maxCosineUserOpenid = s;
            }
        }
        //选出第二大的
        for (String s : currentUserCosineMap.keySet()) {
            if (!s.equals("current_user_openid") && !s.equals(maxCosineUserOpenid) && Double.parseDouble(currentUserCosineMap.get(s).toString()) > secondMaxCosine) {
                secondMaxCosine = Double.parseDouble(currentUserCosineMap.get(s).toString());
                secondCosineUserOpenid = s;
            }
        }

        //如果备选用户数量不足，直接终止推荐函数运行
        if (maxCosineUserOpenid.equals("") || secondCosineUserOpenid.equals(""))
            return ResponseData.functionTerminated();

        //筛选出最大和第二大用户看过，但当前用户没看过的文章
        List<String> recommendedArticles = new ArrayList<>();
        Map<String, String> maxMap = null;
        Map<String, String> secondMap = null;
        //首先找出相似度前两位的用户的兴趣向量map
        for (Map<String, String> vectorMap : userVectorMatrix) {
            if (vectorMap.get("user_openid").equals(maxCosineUserOpenid)) {
                maxMap = vectorMap;
            } else if (vectorMap.get("user_openid").equals(secondCosineUserOpenid)) {
                secondMap = vectorMap;
            }
        }

        List<String> removeList;
        try {
            removeList = new ArrayList<>();
            //将maxMap与当前用户的兴趣向量map做差集
            for (String s : maxMap.keySet()) {
                if (s.equals("user_openid"))
                    continue;
                for (String cs : currentUserMap.keySet()) {
                    if (cs.equals("user_openid"))
                        continue;
                    if (cs.equals(s))
                        removeList.add(s);
                }
                System.out.println("concurrent");
            }
            for (String r : removeList) {
                maxMap.remove(r);
            }

            //将maxMap和secondMap做交集，得出maxMap和secondMap共同看过，但当前用户没有看过的文章
            removeList = new ArrayList<>();
            for (String s : maxMap.keySet()) {
                if (secondMap.get(s) == null) {
                    removeList.add(s);
                }
            }
            for (String r : removeList) {
                maxMap.remove(r);
            }

            //如果maxMap作运算后为空，终止推荐函数
            if (maxMap.size() == 1)
                return ResponseData.functionTerminated();
            //可能要推荐给当前用户的文章列表
            for (String s : maxMap.keySet()) {
                if (!s.equals("user_openid"))
                    recommendedArticles.add(s);
            }
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            System.out.println("相似度前两位用户查找失败");
            return ResponseData.functionTerminated();
        }

        //计算预测出的当前用户评分
        Map<String, String> predictVectorMap = new HashMap<>();

        for (String s : Objects.requireNonNull(maxMap).keySet()) {
            if (!s.equals("user_openid")) {
                predictVectorMap.put(s, String.valueOf(predictVector(currentUserCosineMap, maxMap, secondMap, s)));
            }
        }

        //根据阈值进行推荐
        int threshold;
        int sum = 0;
        for (String s : currentUserMap.keySet()) {
            if (!s.equals("user_openid"))
                sum +=  Integer.parseInt(currentUserMap.get(s));
        }
        threshold = sum / currentUserMap.size();

        System.out.println("阈值："+threshold);

        //筛选超过阈值的文章进行推荐
        removeList.clear();
        for (String ps : predictVectorMap.keySet()) {
            if (Double.parseDouble(predictVectorMap.get(ps)) < threshold)
                removeList.add(ps);
        }
        for (String r : removeList) {
            predictVectorMap.remove(r);
        }

        return ResponseData.ok().setData("recommendedArticles", predictVectorMap);

    }

    private void generateCosineMatrix() {
        Map<String, String> currentUserMap = new HashMap<>();
        currentUserMap.put("1", "5");
        currentUserMap.put("2", "3");
        currentUserMap.put("3", "4");
        currentUserMap.put("4", "4");

        Map<String, String> otherUserMap = new HashMap<>();
        otherUserMap.put("1", "3");
        otherUserMap.put("2", "1");
        otherUserMap.put("3", "2");
        otherUserMap.put("4", "3");
        otherUserMap.put("5", "3");
        //测试
        calculateCosineSimilarity(currentUserMap, otherUserMap);
    }


    private double predictVector(Map<String, Object> currentUserCosineMap, Map<String, String> firstMap, Map<String, String> secondMap, String article_id) {

        double firstCosineSim, secondCosineSim;
        firstCosineSim = Double.parseDouble(currentUserCosineMap.get(firstMap.get("user_openid")).toString());
        secondCosineSim = Double.parseDouble(currentUserCosineMap.get(secondMap.get("user_openid")).toString());

        double firstVectorValue, secondVectorValue;
        firstVectorValue = Double.parseDouble(firstMap.get(article_id));
        secondVectorValue = Double.parseDouble(secondMap.get(article_id));

        return (firstCosineSim * firstVectorValue + secondCosineSim * secondVectorValue) / (firstCosineSim + secondCosineSim);
    }

    /**
     *
     * @param currentUserMap
     * @param otherUserMap
     * @return
     */
    private double calculateCosineSimilarity(Map<String, String> currentUserMap, Map<String, String> otherUserMap) {
        //用于计算分子
        double tempValue01, tempValue02;
        //分子
        double sumValue = 0;
        //分母
        double divisor01 = 0;
        double divisor02 = 0;
        //最终结果
        double cosineValue;
        //两个用户共有的向量
        List<Integer> overlapVectorList = new ArrayList<>();

        //计算分子，求和
        for (String currentUserKey : currentUserMap.keySet()) {
            if (otherUserMap.get(currentUserKey) != null && !currentUserKey.equals("user_openid")) {
                tempValue01 = Double.parseDouble(currentUserMap.get(currentUserKey));
                tempValue02 = Double.parseDouble(otherUserMap.get(currentUserKey));
                sumValue += tempValue01 * tempValue02;
                overlapVectorList.add(Integer.valueOf(currentUserKey));
            }
        }

        //计算分母
        for (Integer integer : overlapVectorList) {
            divisor01 += Double.parseDouble(currentUserMap.get(integer.toString())) * Double.parseDouble(currentUserMap.get(integer.toString()));
            divisor02 += Double.parseDouble(otherUserMap.get(integer.toString())) * Double.parseDouble(otherUserMap.get(integer.toString()));
        }

        //将分母开方
        divisor01 = java.lang.Math.sqrt(divisor01);
        divisor02 = java.lang.Math.sqrt(divisor02);

        //做除法计算余弦相似度
        cosineValue = sumValue / (divisor01 * divisor02);

        System.out.println("cosineValue:"+cosineValue);

        return cosineValue;
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