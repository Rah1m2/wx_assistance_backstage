package com.wx.main.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.main.DAO.UserDAO;
import com.wx.main.POJO.Student;
import com.wx.main.POJO.User;
import com.wx.main.Service.UserService;
import com.wx.main.Util.Decrypt_Util;
import com.wx.main.Util.HttpRequest_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl() {
    }
    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public List<User> getUserList() {
        return userDAO.getUserList();
    }

    public int insertSingleUser(User user) {
        return userDAO.insertSingleUser(user);
    }

    public int deleteSingleUser(User user) {
//        userDAO.deleteSingleUser(user);
        return 0;
    }


    public List<User> getUserByAccount(String UserAccount) {
//        return userDAO.getUserByAccount(UserAccount);
        return null;
    }

    public int updateSingleUser(User user) {
//        userDAO.updateSingleUser(user);
        return 0;
    }

    public String wxLogin(String encryptedData, String iv, String code) {
        final int INSERT_FAILED = 0;
        List<User> user_list;
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            System.out.println("登录凭证不能为空！");
            return "NO";
        }

        //小程序id
        String wxAppID = "wx8b35bb94f164b782";

        //小程序唯一密钥
        String wxAppSecret = "6896ae1a015096e1704fb01cc5ef6d3b";

        //请求的内容
        String grant_type = "authorization_code";

        //请求的参数
        String params = "appid=" + wxAppID + "&secret=" + wxAppSecret + "&js_code=" + code + "&grant_type=" + grant_type;

        //向微信发送请求获取session_key和openid
        String sr = HttpRequest_Util.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

        System.out.println("Result:"+sr);

        JSONObject json = JSONObject.parseObject(sr);

        String session_key = (String)json.get("session_key");

        String openid = (String) json.get("openid");

        //解码，获取明文数据
        JSONObject json_res = null;
        try {
            String result = Decrypt_Util.decrypt(encryptedData, session_key, iv, "UTF-8");
            json_res = JSONObject.parseObject(result);
            System.out.println("res:"+result);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解码异常。");
        }

        user_list = userDAO.getUserByAccount(openid);

        //操作dao层，如果不是第一次登录就返回解码的数据；否则进行插入操作
        if(!user_list.isEmpty()) {
            //判断用户的身份
            user_list.get(0).setUser_identity(String.valueOf(userDAO.identifyUserByOpenid(openid)));
            return JSON.toJSONString(user_list);
        }

        assert json_res != null;
        User regUser = new User(openid, (String) json_res.get("nickName"), json_res.get("gender").toString(), (String) json_res.get("language"), (String) json_res.get("country"), (String) json_res.get("avatarUrl"), "0", null);
        if(INSERT_FAILED == userDAO.insertSingleUser(regUser))
            return "NO";

        //清空user_list
        user_list.clear();

        //将首次登录用户的数据写入list
        user_list.add(regUser);

        return JSON.toJSONString(user_list);
    }

    public String wxRegisterID(Student student) {
        if (0 == userDAO.insertSingleAStuInfo(student))
            return "NO";
        return "YES";
    }
}
