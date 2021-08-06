package com.wx.main.Controller;

import com.wx.main.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/pst")
public class PostingController {

    private UserService userService;

    public PostingController() {
    }

    @Autowired
    public PostingController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/reqPostings")
    @ResponseBody
    public String sendPostings() {

        return null;
    }

    @RequestMapping(value = "/sendPostings")
    @ResponseBody
    public String receivePostings() {

        return null;
    }

}
