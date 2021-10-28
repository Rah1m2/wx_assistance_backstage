package com.wx.main.Service;

import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.User;
import com.wx.main.VO.ResponseData;
import com.wx.main.VO.StudentReserve;

import java.util.List;
import java.util.Map;

public interface AdminService {

    //查看是否被封禁
    ResponseData isBarred(String user_openid);

    //封禁
    ResponseData GarryKing(Map<String, Object> userForm);

    //校验管理员的登录
    boolean verifyLogin(Map<String, Object> loginForm);

    //注册管理员账号
    ResponseData register(Map<String, Object> registerForm);

    //获取用户的信息
    ResponseData getUserInfo();

    //获取学霸信息
    ResponseData getAStuInfo();

    //获取被封禁用户
    ResponseData getBannedInfo();

    //获取未确认的预约(redis)
    ResponseData getUnconfirmedREZ();

    //删除未确认的预约(redis)
    ResponseData delUnconfirmedREZ(String key);

    //获取已经受理的全部预约(mysql)
    ResponseData getAcceptedREZ();

    //删除一条预约信息(mysql)
    ResponseData delAcceptedREZ(int mission_id);

    //修改课程名称
    ResponseData editSortName(Map<String, Object> sortForm);

    //删除课程
    ResponseData delSort(int sort_id);

    //添加课程
    ResponseData addSort(String addSortName);

    //admin界面的查询语句
    ResponseData screenUser(String query);
}
