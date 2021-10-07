package com.wx.main.DAO;

import com.wx.main.POJO.Student;
import com.wx.main.POJO.User;

import java.util.List;

public interface UserDAO {

    //查询用户列表
    List<User> getUserList();

    //增加一条用户信息
    int insertSingleUser(User user);

    //删除一条用户信息
//    int deleteSingleUser(User user);

    //查询用户
    List<User> getUserByAccount(String UserAccount);

    //增加一条学霸信息
    int insertSingleAStuInfo(Student student);

    //判断用户身份
    int identifyUserByOpenid(String String);
}
