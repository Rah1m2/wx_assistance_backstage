package com.wx.main.Service.Impl;

import com.wx.main.DAO.SearchDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.Service.SearchService;
import com.alibaba.fastjson.JSON;
import com.wx.main.Util.Split_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service(value = "searchServiceImpl")
public class SearchServiceImpl implements SearchService {

    private SearchDAO searchDAO;

    @Autowired
    public SearchServiceImpl(SearchDAO searchDAO) {
        this.searchDAO = searchDAO;
    }

    public String getSearchResult(String query) {
        List <Posting> postingList = searchDAO.dynamicSearch(query);
        Split_Util.splitUrl(postingList);
        return JSON.toJSONString(postingList);
    }


}
