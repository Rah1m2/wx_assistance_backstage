package com.wx.main.DAO;

import com.wx.main.POJO.User;

import java.util.List;
import java.util.Map;

public interface AdminDAO {

    Map<String, Object> getAdminByAcPw(Map<String, Object> loginForm);

    List<User> getUserByQuery(String query);
}
