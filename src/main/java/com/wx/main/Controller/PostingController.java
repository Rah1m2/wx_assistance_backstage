package com.wx.main.Controller;

import com.wx.main.Model.Posting;
import com.wx.main.Model.QueryParams;
import com.wx.main.Service.PostingService;
import com.wx.main.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/pst")
public class PostingController {

    private HttpServletRequest request;

    private PostingService postingService;

    public PostingController() {
    }

    @Autowired
    public PostingController(PostingService postingService, HttpServletRequest request) {
        this.postingService = postingService;
        this.request = request;
    }

    /**
     * 前端向后端请求数据
     * @param queryParams 前端传回的请求参数
     * @return 返回装有数据的json串
     */
    @RequestMapping(value = "/reqPostings")
    @ResponseBody
    public String sendPostings(QueryParams queryParams) {
        showUrl();
        return postingService.getRequiredPostings(queryParams);
    }

    @RequestMapping(value = "/sendPostings")
    @ResponseBody
    public String receivePostings(Posting posting) {
        if (postingService.storeSinglePosting(posting) == 0)
            return "NO";
        return "YES";
    }

    private void showUrl(){
        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath() + "?" + request.getQueryString());
    }

}
