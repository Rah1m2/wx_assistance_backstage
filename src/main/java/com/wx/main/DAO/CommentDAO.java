package com.wx.main.DAO;

import com.wx.main.POJO.Comment;
import com.wx.main.POJO.Thumb;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDAO {
    //返回所有评论
    List<Comment> getCommentList();

    //按user_id返回评论
    List<Comment> getCommentByUserId();

    //按article_id返回评论
    List<Comment> getCommentByArticleId(String article_id);

    //插入单条评论
    int insertSingleComment(Comment comment);

    //插入评论点赞记录
    int addCommentThumbs(@Param("addList")List<Thumb> addThumbs);

    //删除点赞记录
    int delCommentThumbs(@Param("delList")List<Thumb> delThumbs);

    //更新点赞人数
    int updateThumbsCount(int thumbs_count);
}
