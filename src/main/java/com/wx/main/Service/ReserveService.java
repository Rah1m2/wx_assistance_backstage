package com.wx.main.Service;

import com.wx.main.POJO.Reserve;

import java.util.List;
import java.util.Map;

public interface ReserveService {

    List<Reserve> getAStudentInfo(Map<String, Object> queryForm);

    String saveReservedInfo(Reserve reserve);

}
