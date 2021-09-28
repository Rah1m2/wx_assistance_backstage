package com.wx.main.Service;

import com.wx.main.POJO.Reserve;

import java.util.List;

public interface ReserveService {

    List<Reserve> getReserveInfo(int sort_id);

}
