package com.wx.main.Service;

import com.wx.main.VO.QueryParams;
import com.wx.main.VO.ResponseData;

import java.util.List;
import java.util.Map;

public interface SearchService {
    String getSearchResult(String query, QueryParams queryParams);

    String getUidByAid(int article_id);

    ResponseData getStatistics();

    ResponseData getPSTCountGroupBySort();
}
