package com.wx.main.Service;

import com.wx.main.VO.QueryParams;

public interface SearchService {
    String getSearchResult(String query, QueryParams queryParams);

    String getUidByAid(int article_id);
}
