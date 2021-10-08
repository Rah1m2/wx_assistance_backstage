package com.wx.main.DAO;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReserveDAO {

    //通过sort_id查询学霸的信息
    List<Student> getAStudentInfoBySortId(Map<String, Object> queryForm);

    //通过
    List<Reserve> getCurReservedInfoByUserOpenid(Map<String, Object> queryForm);

    //过滤预订信息
    List<Student> filterAStudentInfo(@Param("aStuList")List<Student> aStuList);

    //暂时关闭ONLY_FULL_GROUP_BY规则
    List<Map> DisableONLY_FULL_GROUP_BY();

    //插入一条预订信息
    int insertReserveInfo(Reserve reserve);

}
