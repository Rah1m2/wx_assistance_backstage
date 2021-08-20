package com.wx.main.DAO;

import com.wx.main.POJO.Posting;
import com.wx.main.POJO.Sort;
import javafx.geometry.Pos;

import java.util.List;

public interface PostingDAO {

    //返回所有帖子
    List<Posting> getPostingList();

    //按时间获取所有帖子
    List<Posting> getPostingListOrderByTime();

    //获取分类信息
    List<Sort> getSortInfo();

    //按sort_id获取帖子
    List<Posting> getPostingBySortId(int sort_id);

    //按照article_id获取帖子
    List<Posting> getPostingByArticleId(int article_id);

    //按照user_openId获取帖子
    List<Posting> getPostingByUserOpenId(String user_openid);

    //按三种不同的id分别获取帖子
    List<Posting> getPostingById(int id);

    //增加一条帖子信息
    int insertSinglePosting(Posting posting);

    //删除一条帖子信息
    int deleteSinglePosting(String article_id);

    //获取当前用户发表的帖子数目
    int getCurrentUserPstCount(String user_openid);

    //更新当前用户的点赞数目
//    int updateCurrentUserThumbCount();
}
