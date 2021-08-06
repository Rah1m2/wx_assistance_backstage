package com.wx.main.Controller;

import com.alibaba.fastjson.JSON;
import com.wx.main.Service.UserService;
import com.wx.main.Util.Generate_TempKey_Util;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tencent.cloud.CosStsClient;

import java.util.TreeMap;


@Controller
@RequestMapping("/sys")
public class SysController {

    private UserService userService;

    public SysController() {
    }

    @Autowired
    public SysController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/reqKey")
    @ResponseBody
    public String sendKey(String bucket, String region){
        //测试用
        System.out.println("bucket："+bucket);
        System.out.println("region："+region);
        JSONObject credential = Generate_TempKey_Util.getKey(bucket, region);
        return JSON.toJSONString(credential);
    }



    /*---------------------------------测试用接口-------------------------------------*/

}