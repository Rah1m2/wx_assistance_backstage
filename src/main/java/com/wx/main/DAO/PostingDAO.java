package com.wx.main.DAO;

import com.wx.main.Model.Posting;

import java.util.List;

public interface PostingDAO {

    //返回所有帖子
    List<Posting> getPostingList();

    //增加一条帖子信息
    int insertSinglePosting(Posting posting);

}
