package com.wx.main.DAO;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.VO.RedisCustomer;
import com.wx.main.VO.ResponseData;
import com.wx.main.VO.StudentReserve;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReserveDAO {

    //通过sort_id查询学霸的信息
    List<Student> getAStudentInfoBySortId(Map<String, Object> queryForm);

    //通过user_openid查询预约的信息
    List<StudentReserve> getREZInfoOfCurByUserOpenid(String user_openid);

    //通过
    List<StudentReserve> getCurAcceptedREZInfoByUserOpenid(Map<String, Object> queryForm);

    //获取还没有失效的预订信息
    List<Student> getInvalidStudentInfo(@Param("aStuList")List<Student> aStuList);

    //获取已有两个预约的用户信息
    List<Student> getUnavailableStudentInfo();

    //暂时关闭ONLY_FULL_GROUP_BY规则
    List<Map> DisableONLY_FULL_GROUP_BY();

    //插入一条预订信息
    int insertReserveInfo(Reserve reserve);

    //根据mission_id删除预约
    int delRezByMissionId(int x);

    //根据user_openid删除预约
    int delRezByUserOpenid(String user_openid);

    //通过user_openid查询contact_detail
    String getContactDetailByUserOpenid(String user_openid);

    //修改预约所设置的截止日期
    int updateRezDeadlineByMissionId(Map<String, Object> disableForm);

}
