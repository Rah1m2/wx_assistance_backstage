package com.wx.main.DAO;

import com.wx.main.POJO.User;
import com.wx.main.VO.StudentReserve;

import java.util.List;
import java.util.Map;

public interface AdminDAO {

    //封禁
    int barAccount(Map<String, Object> userForm);

    //取消封禁
    int unbarAccount(Map<String, Object> userForm);

    //获取管理员信息
    Map<String, Object> getAdminByAcPw(Map<String, Object> loginForm);

    //添加管理员
    int insertAdmin(Map<String, Object> registerForm);

    //获取用户
    List<User> getUserByQuery(String query);

    //获取预订信息
    List<StudentReserve> getReservations();

    //从MySQL删除一条预约
    boolean deleteReservation(int mission_id);

    //插入一条课程信息
    int insertSort(String addSortName);

    //删除一条课程信息
    int deleteSort(int sort_id);

    //修改课程名称
    int updateSort(Map<String, Object> sortForm);

    int deleteAStuByQuery(Map<String, Object> userForm);

}
