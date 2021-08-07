package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.PostingDAO;
import com.wx.main.Model.Posting;
import com.wx.main.Model.QueryParams;
import com.wx.main.Service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "postingServiceImpl")
public class PostingServiceImpl implements PostingService {

    private PostingDAO postingDAO;

    public PostingServiceImpl() {
    }

    @Autowired
    public PostingServiceImpl(PostingDAO postingDAO) {
        this.postingDAO = postingDAO;
    }

    public String getRequiredPostings(QueryParams queryParams) {
//        PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());
        JSONObject jsonObject = new JSONObject();
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObject.put("PageNum",queryParams.getPageNum());
        jsonObject.put("PageSize",queryParams.getPageSize());
        PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());
        jsonObject.put("articleList",postingDAO.getPostingBySortId(queryParams.getSort_id()));
//        = postingDAO.getPostingBySortId(queryParams.getSort_id());

        return JSON.toJSONString(jsonObject);
    }
}
