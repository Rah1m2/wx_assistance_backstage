package com.wx.main.Controller;


import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.wx.main.DAO.UserDAO;
import com.wx.main.Model.User;
import com.wx.main.Service.UserService;
import com.wx.main.Util.Decrypt_Util;
import com.wx.main.Util.HttpRequest_Util;
import org.mortbay.util.ajax.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/msg")
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public String login(String encryptedData,String iv,String code){
        //测试用
        System.out.println("encryptedData:"+encryptedData);
        System.out.println("iv:"+iv);
        System.out.println("code:"+code);

        return userService.wxLogin(encryptedData,iv,code);
    }


    /*---------------------------------测试用接口-------------------------------------*/

    @RequestMapping("/toFront")
    @ResponseBody
    public String hello(){
        return "this is the testing signal broadcasted by m1chael";
    }

    @RequestMapping(value = "/toBack",method = RequestMethod.POST)
    @ResponseBody
    public User bye(@RequestBody User user){
        System.out.println("我是:"+user);
        return user;
    }
}