package com.wx.main.DAO;

import com.wx.main.POJO.Posting;

import java.util.List;

public interface SearchDAO {

    //模糊搜索
    List<Posting> dynamicSearch(String query);

    //通过article_id获取user_openid
    String getUserOpenidByArticleId(int article_id);
}
