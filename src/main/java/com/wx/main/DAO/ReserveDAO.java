package com.wx.main.DAO;

import com.wx.main.POJO.Reserve;

import java.util.List;

public interface ReserveDAO {

    List<Reserve> getAStudentInfoBySortId(int sort_id);

}
