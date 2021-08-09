package com.wx.main.DAO;

import com.wx.main.POJO.Posting;
import com.wx.main.POJO.Sort;

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

    //增加一条帖子信息
    int insertSinglePosting(Posting posting);

}
