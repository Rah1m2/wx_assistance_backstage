package com.wx.main.Service;


import com.wx.main.POJO.Posting;
import com.wx.main.POJO.QueryParams;

public interface PostingService {

    //按需求查询帖子信息
    String getRequiredPostings(QueryParams queryParams);

    //存储单条帖子信息到数据库
    int storeSinglePosting(Posting posting);

    String getSortInfo();
}
