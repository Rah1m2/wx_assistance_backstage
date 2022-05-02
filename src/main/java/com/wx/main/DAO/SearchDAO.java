package com.wx.main.DAO;

import com.wx.main.POJO.Posting;
import com.wx.main.POJO.User;
import com.wx.main.VO.StudentReserve;

import java.util.List;
import java.util.Map;

public interface SearchDAO {

    //模糊搜索（文章）
    List<Posting> dynamicSearch(String query);

    //通过article_id获取user_openid
    String getUserOpenidByArticleId(int article_id);

    //检索数据（后台）
    List<User> searchAdmin(String query);

    //查询整体统计数据
    List<Integer> returnStatsMap();

    //查询文章数量并按照科目分类
    List<Map<String, Integer>> returnPostingCountGroupBySort();
}
