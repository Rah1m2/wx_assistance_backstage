package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.CommentDAO;
import com.wx.main.POJO.Comment;
import com.wx.main.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "commentServiceImpl")
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public String getCommentByPosting(String article_id) {
        //用于封装两个属性的json对象
        JSONObject jsonObject = new JSONObject();
        //属性1
        List<Comment> commentList = commentDAO.getCommentByArticleId(article_id);
        //评论列表
        jsonObject.put("commentList",commentList);
        //属性2：评论的总条数
        jsonObject.put("commentTotal",commentList.size());
        //返回String
        return JSON.toJSONString(jsonObject);
    }

    public String getCommentByUser() {
        return null;
    }

    public String insertSingleComment(Comment comment) {
        if (commentDAO.insertSingleComment(comment) == 0)
            return "NO";
        return "YES";
    }
}
