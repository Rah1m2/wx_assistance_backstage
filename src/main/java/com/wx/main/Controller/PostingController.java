package com.wx.main.Controller;

import com.wx.main.POJO.Posting;
import com.wx.main.POJO.QueryParams;
import com.wx.main.Service.PostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pst")
public class PostingController {

//    private HttpServletRequest request;

    private PostingService postingService;

    public PostingController() {
    }

    @Autowired
    public PostingController(PostingService postingService) {
        this.postingService = postingService;
//        this.request = request;
    }

    /**
     * 前端向后端请求数据
     * @param queryParams 前端传回的请求参数
     * @return 返回装有数据的json串
     */
    @RequestMapping(value = "/reqPostings")
    @ResponseBody
    public String sendPostings(QueryParams queryParams) {
//        showUrl();
        return postingService.getRequiredPostings(queryParams);
    }

    /**
     * 获取帖子的内容（问题描述，图片等）
     * @param article_id 文章id
     * @return 返回评论文章的String串
     */
    @RequestMapping(value = "/reqPostingDetail")
    @ResponseBody
    public String sendPostingDetail(int article_id) {
        return postingService.getPostingDetail(article_id);
    }

    /**
     * 返回帖子的标题和第一张图片
     * @param posting 帖子的实体类
     * @return 同下
     */
    @RequestMapping(value = "/sendPostingInfo")
    @ResponseBody
    public String receivePosting(Posting posting) {
        return postingService.insertSinglePosting(posting);
    }

    /**
     * 返回帖子的分类信息
     * @return 返回String串
     */
    @RequestMapping(value = "/reqSortInfo")
    @ResponseBody
    public String sendSortInfo() {
        return postingService.getSortInfo();
    }

//    private void showUrl(){
//        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
//                + ":" +request.getServerPort()
//                + request.getServletPath() + "?" + request.getQueryString());
//    }

}
