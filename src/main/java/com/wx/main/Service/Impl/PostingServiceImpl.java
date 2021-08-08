package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.PostingDAO;
import com.wx.main.Model.Posting;
import com.wx.main.Model.QueryParams;
import com.wx.main.Service.PostingService;
import com.wx.main.Util.Transcoding_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    /**
     *
     * @param queryParams
     * @return
     */
    public String getRequiredPostings(QueryParams queryParams) {
        //嵌套json
        JSONObject jsonObject = new JSONObject();

        //页面下标（从第几页开始查询）
        jsonObject.put("pageNum",queryParams.getPageNum());

        //查询的页数
        jsonObject.put("pageSize",queryParams.getPageSize());

        //分页查询的对象
        Page page = PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());

        //0代表选择全部
        if (queryParams.getSort_id() == 0)
            jsonObject.put("articleList",postingDAO.getPostingList());
        else
            jsonObject.put("articleList",postingDAO.getPostingBySortId(queryParams.getSort_id()));

        //帖子总数
        jsonObject.put("articleTotal",page.getTotal());

        jsonObject.put("articleList",Transcoding_Util.TranscodePosting((List) jsonObject.get("articleList")));
//        jsonObject = Transcoding_Util.TranscodePosting((List) jsonObject.get("articleList"));

        //返回String串,注解会自动转换为json
        return JSON.toJSONString(jsonObject);
    }

    public int storeSinglePosting(Posting posting) {
        return postingDAO.insertSinglePosting(posting);
    }
}
