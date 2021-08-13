package com.wx.main.Controller;

import com.wx.main.POJO.Comment;
import com.wx.main.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/cmt")
public class CommentController {

//    private HttpServletRequest request;

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
//        this.request = request;
    }

    @RequestMapping(value = "/sendComment")
    @ResponseBody
    public String receiveComment(Comment comment) {
        return commentService.insertSingleComment(comment);
    }

    @RequestMapping(value = "/reqComment")
    @ResponseBody
    public String sendComment(String article_id) {
        System.out.println("test: ");
//        showUrl();
        return commentService.getCommentByPosting(article_id);
    }

//    private void showUrl(){
//        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
//                + ":" +request.getServerPort()
//                + request.getServletPath() + "?" + request.getQueryString());
//    }
}
