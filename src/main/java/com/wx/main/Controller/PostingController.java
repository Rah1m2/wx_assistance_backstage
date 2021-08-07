package com.wx.main.Controller;

import com.wx.main.Model.QueryParams;
import com.wx.main.Service.PostingService;
import com.wx.main.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pst")
public class PostingController {

    private PostingService postingService;

    public PostingController() {
    }

    @Autowired
    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @RequestMapping(value = "/reqPostings")
    @ResponseBody
    public String sendPostings(QueryParams queryParams) {
        return postingService.getRequiredPostings(queryParams);
    }

    @RequestMapping(value = "/sendPostings")
    @ResponseBody
    public String receivePostings() {

        return null;
    }

}
