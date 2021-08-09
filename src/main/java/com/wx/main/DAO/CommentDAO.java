package com.wx.main.DAO;

import com.wx.main.POJO.Comment;

import java.util.List;

public interface CommentDAO {
    //返回所有评论
    List<Comment> getCommentList();

    //按user_id返回评论
    List<Comment> getCommentByUserId();

    //按article_id返回评论
    List<Comment> getCommentByArticleId();
}
