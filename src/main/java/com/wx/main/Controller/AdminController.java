package com.wx.main.Controller;

import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.wx.main.POJO.Comment;
import com.wx.main.Service.AdminService;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData adminRegister(@RequestBody Map<String, Object> registerForm) {
        return adminService.register(registerForm);
    }

    @RequestMapping(value = "/reqUserInfo")
    @ResponseBody
    public ResponseData sendUserInfo() {
        return adminService.getUserInfo();
    }

    @RequestMapping(value = "/reqAStuInfo")
    @ResponseBody
    public ResponseData sendAStuInfo() {
        return adminService.getAStuInfo();
    }

    @RequestMapping(value = "/reqBannedInfo")
    @ResponseBody
    public ResponseData sendBannedInfo() {
        return adminService.getBannedInfo();
    }

    @RequestMapping(value = "/reqUnconfirmedREZ")
    @ResponseBody
    public ResponseData sendRedisReservationInfo() {
        return adminService.getUnconfirmedREZ();
    }

    @RequestMapping(value = "/reqAcceptedREZ")
    @ResponseBody
    public ResponseData sendAcceptedREZInfo() {
        return adminService.getAcceptedREZ();
    }

    @RequestMapping(value = "/delAcceptedREZ")
    @ResponseBody
    public ResponseData delReservationFromMySQL(String key) {
        return adminService.delAcceptedREZ(Integer.parseInt(key));
    }

    @RequestMapping(value = "/delUnconfirmedREZ")
    @ResponseBody
    public ResponseData delReservationFromRedis(String key) {
        return adminService.delUnconfirmedREZ(key);
    }

    @RequestMapping(value = "/editSortName", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData editSortName(@RequestBody Map<String, Object> sortForm) {
        return adminService.editSortName(sortForm);
    }

    @RequestMapping(value = "/delSort/{id}")
    @ResponseBody
    public ResponseData delSort(@PathVariable("id") int sort_id) {
        System.out.println("sort_id:"+sort_id);
        return adminService.delSort(sort_id);
    }

    @RequestMapping(value = "/addSort")
    @ResponseBody
    public ResponseData addSort(String addSortName) {
        return adminService.addSort(addSortName);
    }

    @RequestMapping(value = "/YouAreBarred", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData BarredSomeOne(@RequestBody Map<String, Object> userForm) {
        return adminService.GarryKing(userForm);
    }

}
