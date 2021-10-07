package com.wx.main.Controller;


import com.wx.main.POJO.Student;
import com.wx.main.POJO.User;
import com.wx.main.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController() {
    }

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login")
    public String login(String encryptedData,String iv,String code) {
        //测试用
//        System.out.println("encryptedData:"+encryptedData);
//        System.out.println("iv:"+iv);
//        System.out.println("code:"+code);
        return userService.wxLogin(encryptedData,iv,code);
    }

    @RequestMapping(value = "/registerIdentity")
    public String registerIdentity(Student student) {
        return userService.wxRegisterID(student);
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