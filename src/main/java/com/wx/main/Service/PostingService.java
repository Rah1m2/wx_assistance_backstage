package com.wx.main.Service;


import com.wx.main.POJO.Posting;
import com.wx.main.POJO.QueryParams;
import com.wx.main.POJO.Thumb;

import java.util.List;

public interface PostingService {

    //按需求查询帖子封面信息
    String getRequiredPostings(QueryParams queryParams);

    //按当前用户id查询
    public String getMyPostings (String user_openid);

    //获取帖子详细信息
    String getPostingDetail(int article_id);

    //存储单条帖子信息到数据库
    String insertSinglePosting(Posting posting);

    //删除一条帖子信息
    String deleteSinglePosting(String article_id);

    //获取分类信息
    String getSortInfo();

    //获取当前用户的发帖数量
    int getCurUserPstCount(String user_openid);

}
