package com.wx.main.Aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.main.DAO.PostingDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.Util.Split_Util;
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
import java.util.*;

@ControllerAdvice
public class responseAspect implements ResponseBodyAdvice<Object> {

    private RedisTemplate_Util redisTemplate_util;
    private PostingDAO postingDAO;

    @Autowired
    public responseAspect(RedisTemplate_Util redisTemplate_util, PostingDAO postingDAO) {
        this.redisTemplate_util = redisTemplate_util;
        this.postingDAO = postingDAO;
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

//        ServletServerHttpResponse responseTemp = (ServletServerHttpResponse) serverHttpResponse;
//        HttpServletResponse response = responseTemp.getServletResponse();

        //将body转换方便操作
        JSONObject jsonObject;
        try {
            jsonObject = com.alibaba.fastjson.JSONObject.parseObject(String.valueOf(body));
        } catch (Exception e) {
            System.out.println("---response passed---");
            return body;
        }

        //body里的文章列表
        List<Posting> articleList = (List<Posting>) jsonObject.get("articleList");

        //通过截取响应头获得token，进而获得user_openid
        JSONObject tokenObj = JSON.parseObject(httpServletRequest.getHeader("token"));
        Map<String, String> userMap = (Map<String, String>) tokenObj.get("userInfo");
        String user_openid = userMap.get("user_openid");

        //查询redis中协同过滤算法计算出的文章
        String key = "db_graduate_design:user_vector_predict:" + user_openid + ":*";
        Set<String> querySet = (Set<String>) redisTemplate_util.queryKey(key);

        //用读到的redis数据查询MySQL
        String[] regexStrs;  //用于存储分割的redis字符串
        int article_id;
        List<Posting> posting;  //用于存储基于文章id查出的推荐文章
        List<Posting> recArticleList = new ArrayList<>();  //推荐文章的列表

        //对redis查出的内容分割并查询MySQL
        for (String s : querySet) {
            regexStrs = s.split(":");
            article_id = Integer.parseInt(regexStrs[3]);
            posting = postingDAO.getPostingByArticleId(article_id);
            recArticleList.add(posting.get(0));
        }

        //将图片url分割
        Split_Util.splitUrl(recArticleList);

        //分割后将查出来的推荐文章加到原有的文章头部
        articleList.addAll(0, recArticleList);
        jsonObject.put("articleList", articleList);

        //文章数目改为增加后的
        int articleTotal = (int) jsonObject.get("articleTotal");
        articleTotal += recArticleList.size();
        jsonObject.put("articleTotal", articleTotal);

        //TODO 增加recArticleArr字段
        List<Integer> recArticleArr = new ArrayList<>();
        for (Posting posting1 : recArticleList) {
            recArticleArr.add(posting1.getArticle_id());
        }
        jsonObject.put("recArticleArr", recArticleArr);

        //放入body中发送给前端
        body = jsonObject.toJSONString();
        return body;
    }
}
