package com.wx.main.Service;

import com.wx.main.POJO.Comment;

public interface CommentService {

    String getCommentByPosting(String article_id);

    String getCommentByUser();

    int insertSingleComment(Comment comment);

    //存储当前用户的点赞记录
    String updateCurUserThumbs(String user_openid, String thumbs);
}
