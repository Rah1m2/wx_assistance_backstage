package com.wx.main.Service;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;

import java.util.List;
import java.util.Map;

public interface ReserveService {

    //获取空闲学霸的信息
    List<Student> getAStudentInfoFromDB(Map<String, Object> queryForm);

    //获取待处理的预约信息
    List<Reserve> getBacklogInfo(Map<String, Object> queryForm);

    //确认预约后将数据存入持久化数据库
    String saveReservedInfoToDB(Reserve reserve);

    //发起预约后将未确认状态的预约暂存NoSql
    String saveReservedInfoToRedis(Reserve reserve);

    //处理（拒绝或接受）预订状态
    String processReserve(Reserve reserve);

    //查询当前用户已预定的订单信息
    List<Reserve> getCurReservedInfo(Map<String, Object> queryForm);

}
