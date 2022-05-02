package com.wx.main.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.SearchDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.VO.QueryParams;
import com.wx.main.Service.SearchService;
import com.alibaba.fastjson.JSON;
import com.wx.main.Util.Split_Util;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public ResponseData getStatistics() {
        List<Integer> statsList = searchDAO.returnStatsMap();
        Map<String, Integer> statsMap = new HashMap<>();
        statsMap.put("posting_count", statsList.get(0));
        statsMap.put("user_count", statsList.get(1));
        statsMap.put("reserve_count", statsList.get(2));
        return ResponseData.ok().setData("statsMap", statsMap);
    }

    @Override
    public ResponseData getPSTCountGroupBySort() {
        List<Map<String, Integer>> pstCountList = searchDAO.returnPostingCountGroupBySort();
        return ResponseData.ok().setData("pstCountList", pstCountList);
    }


}
