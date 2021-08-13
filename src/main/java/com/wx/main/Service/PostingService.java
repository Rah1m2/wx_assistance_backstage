package com.wx.main.Service;


import com.wx.main.POJO.Posting;
import com.wx.main.POJO.QueryParams;

import java.util.List;

public interface PostingService {

    //按需求查询帖子封面信息
    String getRequiredPostings(QueryParams queryParams);

    //获取帖子详细信息
    String getPostingDetail(int article_id);

    //存储单条帖子信息到数据库
    String insertSinglePosting(Posting posting);

    //获取分类信息
    String getSortInfo();
}
