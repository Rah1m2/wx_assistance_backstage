package com.wx.main.Controller;

import com.wx.main.VO.QueryParams;
import com.wx.main.POJO.*;
import com.wx.main.Service.PostingService;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
     * 前端向后端请求数帖子封面数据
     * @param queryParams 前端传回的请求参数
     * @return 返回装有数据的json串
     */
    @RequestMapping(value = "/reqPostings")
    public String sendPostings(QueryParams queryParams) {
//        showUrl();
        System.out.println("qParam2:"+queryParams);
        return postingService.getRequiredPostings(queryParams);
    }

    @RequestMapping(value = "/reqMyPostings")
    public String sendMyPosting(String user_openid) {
        return postingService.getMyPostings(user_openid);
    }

    /**
     * 获取帖子的内容（问题描述，图片等）
     * @param article_id 文章id
     * @return 返回评论文章的String串
     */
    @RequestMapping(value = "/reqPostingDetail")
    public String sendPostingDetail(int article_id) {
        return postingService.getPostingDetail(article_id);
    }

    /**
     * 插入一条帖子
     * @param posting 帖子的实体类
     * @return 同下
     */
    @RequestMapping(value = "/sendPostingInfo")
    public String receivePosting(Posting posting) {
        return postingService.insertSinglePosting(posting);
    }

    /**
     * 删掉一条帖子
     * @param article_id posting 帖子的实体类
     * @return 删除成功返回YES,删除失败返回NO
     */
    @RequestMapping(value = "/deletePosting")
    public String deletePosting(String article_id) {
        return postingService.deleteSinglePosting(article_id);
    }

    /**
     * 返回帖子的分类信息
     * @return 返回String串
     */
    @RequestMapping(value = "/reqSortInfo")
    public ResponseData sendSortInfo() {
        return postingService.getSortInfo();
    }

    /**
     * 返回当前用户发表的帖子数目
     * @return 返回数值
     */
    @RequestMapping(value = "/reqMyPostingCount")
    public int sendMyPostingCount(String user_openid) {
        return postingService.getCurUserPstCount(user_openid);
    }





//    private void showUrl(){
//        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
//                + ":" +request.getServerPort()
//                + request.getServletPath() + "?" + request.getQueryString());
//    }

}
