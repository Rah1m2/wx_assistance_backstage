package com.wx.main.Service;

import com.wx.main.POJO.Comment;

public interface CommentService {

    String getCommentByPosting(String article_id);

    String getCommentByUser();

    String insertSingleComment(Comment comment);
}
