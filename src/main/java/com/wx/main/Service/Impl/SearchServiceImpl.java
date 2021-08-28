package com.wx.main.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.SearchDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.VO.QueryParams;
import com.wx.main.Service.SearchService;
import com.alibaba.fastjson.JSON;
import com.wx.main.Util.Split_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    private SearchDAO searchDAO;

    @Autowired
    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    public String getSearchResult(String query, QueryParams queryParams) {

        //分页查询的对象
        PageHelper.startPage(queryParams.getPageNum(),queryParams.getPageSize());

        //查询结果
        List <Posting> postingList = searchDAO.dynamicSearch(query);

        //分割url
        Split_Util.splitUrl(postingList);

        return JSON.toJSONString(postingList);
    }

    public String getUidByAid(int article_id) {
        return searchDAO.getUserOpenidByArticleId(article_id);
    }


}
