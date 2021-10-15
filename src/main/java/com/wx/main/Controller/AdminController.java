package com.wx.main.Controller;

import com.wx.main.POJO.Comment;
import com.wx.main.Service.AdminService;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData adminLogin(@RequestBody Map<String, Object> loginForm) {
        if (adminService.verifyLogin(loginForm))
            return ResponseData.ok();
        return ResponseData.unauthorized();
    }

    @RequestMapping(value = "/reqUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData sendUserInfo(@RequestBody Map<String, Integer> queryForm) {
        return adminService.getUserInfo(queryForm);
    }
}
