package com.wx.main.Service;

import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.User;
import com.wx.main.VO.ResponseData;

import java.util.List;
import java.util.Map;

public interface AdminService {

    //校验管理员的登录
    boolean verifyLogin(Map<String, Object> loginForm);

    //获取用户的信息
    ResponseData getUserInfo(Map<String, Integer> queryForm);

}
