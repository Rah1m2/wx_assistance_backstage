package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wx.main.DAO.CommentDAO;
import com.wx.main.POJO.Comment;
import com.wx.main.POJO.Thumb;
import com.wx.main.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "commentServiceImpl")
public class CommentServiceImpl implements CommentService {

    private CommentDAO commentDAO;

    @Autowired
    public CommentServiceImpl(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    public String getCommentByPosting(String article_id) {
//        Page page = PageHelper.startPage();
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

    public String getCurArtThumbs(String article_id) {
        return JSON.toJSONString(commentDAO.getThumbByArticleId(article_id));
    }

    public int insertSingleComment(Comment comment) {
        commentDAO.insertSingleComment(comment);
        return comment.getComment_id();
    }

    public int delSingleComment(int comment_id) {
        return commentDAO.delSingleComment(comment_id);
    }

    public String updateCurUserThumbs(String user_openid, String thumbs) {

        System.out.println("thumbs:"+thumbs);
        //把点赞有关集合转换为json
        JSONObject jsonObject = com.alibaba.fastjson.JSONObject.parseObject(thumbs);

        //提取点赞集合
        JSONArray tmpAdd = (JSONArray) jsonObject.get("addThumbs");

        //提取取消点赞集合
        JSONArray tmpDel = (JSONArray) jsonObject.get("delThumbs");

        //转换为json字符串
        String jsonStrAdd = JSONObject.toJSONString(tmpAdd, SerializerFeature.WriteClassName);
        String jsonStrDel = JSONObject.toJSONString(tmpDel, SerializerFeature.WriteClassName);

        //再把点赞/取消点赞的评论的字符串转换为集合
        List<Integer> addList = JSONObject.parseArray(jsonStrAdd, Integer.class);//把字符串转换成集合
        List<Integer> delList = JSONObject.parseArray(jsonStrDel, Integer.class);

        //声明临时对象
        Thumb thumb = new Thumb(0,user_openid);

        //最终拼凑起来需要插入数据库的集合
        if (!addList.isEmpty()) {
            List<Thumb> combineAddList = new ArrayList<Thumb>();
            for (Integer anAddList : addList) {
                thumb.setComment_id(anAddList);
                combineAddList.add(thumb);
                thumb = new Thumb(0, user_openid);
            }
            commentDAO.addCommentThumbs(combineAddList);
        }
//        commentDAO.

        //最终拼凑起来需要删除数据库的集合
        if (!delList.isEmpty()) {
            List<Thumb> combineDelList = new ArrayList<Thumb>();
            for (Integer anDelList : delList) {
                thumb.setComment_id(anDelList);
                combineDelList.add(thumb);
                thumb = new Thumb(0, user_openid);
            }
            commentDAO.delCommentThumbs(combineDelList);
        }

        //更新点赞人数,要写个foreach
        if (!tmpAdd.isEmpty())
            commentDAO.updateThumbsCount(tmpAdd);
        else if (!tmpDel.isEmpty())
            commentDAO.updateThumbsCount(tmpDel);

        return "YES";
    }
}
