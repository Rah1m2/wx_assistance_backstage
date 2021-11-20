package com.wx.main.Service;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.VO.RedisCustomer;
import com.wx.main.VO.ResponseData;
import com.wx.main.VO.StudentReserve;

import java.util.List;
import java.util.Map;

public interface ReserveService {

    //获取空闲学霸的信息
    List<Student> getAStudentInfoFromDB(Map<String, Object> queryForm);

    //获取待处理的预约信息
    List<RedisCustomer> getBacklogInfo(Map<String, Object> queryForm);

    //确认预约后将数据存入持久化数据库
    String saveReservedInfoToDB(Reserve reserve);

    //发起预约后将未确认状态的预约暂存NoSql
    String saveReservedInfoToRedis(RedisCustomer customer);

    //处理（拒绝或接受）预订状态
    String processReserve(Reserve reserve, boolean isAccept);

    //查询当前用户已接受的别人预订的订单信息
    List<StudentReserve> getCurAcceptedREZInfo(Map<String, Object> queryForm);

    //获取当前用户预订别人的订单信息
    Map<String, Object> getReservationInfoOfCur(String user_openid);

    //结束正在进行中的预订
    ResponseData endProcReservation(Map<String, Object> disableForm);

    //获取当前用户的联系方式
    ResponseData getCurContactDetail(String user_openid);

    ResponseData isRedisContainAStu(String user_openid);
}
