package com.wx.main.Service;

import com.wx.main.POJO.Comment;

public interface CommentService {

    String getCommentByPosting(String article_id);

    String getCommentByUser();

    String getCurArtThumbs(String article_id);

    int insertSingleComment(Comment comment);

    int delSingleComment(int comment_id);

    //存储当前用户的点赞记录
    String updateCurUserThumbs(String user_openid, String thumbs);
}
