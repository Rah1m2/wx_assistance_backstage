package com.wx.main.Controller;

import com.wx.main.POJO.Comment;
import com.wx.main.VO.QueryParams;
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
    public int receiveComment(Comment comment) {
        return commentService.insertSingleComment(comment);
    }

    @RequestMapping(value = "/reqComment")
    @ResponseBody
    public String sendComment(QueryParams queryParams) {
//        showUrl();
        System.out.println("Params3:"+queryParams);
        return commentService.getCommentByPosting(String.valueOf(queryParams.getArticle_id()));
    }

    /**
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/sendThumbs")
    @ResponseBody
    public String receiveThumbs(String user_openid, String thumbs) {
        commentService.updateCurUserThumbs(user_openid,thumbs);
        return "test";
    }

    @RequestMapping(value = "/reqCurArtThumbs")
    @ResponseBody
    public String sendCurArtThumbs(String article_id) {
        return commentService.getCurArtThumbs(article_id);
    }

    @RequestMapping(value = "/delComment")
    @ResponseBody
    public int delComment(int comment_id) {
        return commentService.delSingleComment(comment_id);
    }
//    private void showUrl(){
//        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
//                + ":" +request.getServerPort()
//                + request.getServletPath() + "?" + request.getQueryString());
//    }
}
