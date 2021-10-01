package com.wx.main.DAO;

import com.wx.main.POJO.Reserve;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReserveDAO {

    List<Reserve> getAStudentInfoBySortId(Map<String, Object> queryForm);

    List<Reserve> filterAStudentInfo(@Param("aStuList")List<Reserve> aStuList);

    List<Map> DisableONLY_FULL_GROUP_BY();

    int insertReserveInfo(Reserve reserve);

}
