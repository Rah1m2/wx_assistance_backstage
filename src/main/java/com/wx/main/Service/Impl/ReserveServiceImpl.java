package com.wx.main.Service.Impl;

import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.Reserve;
import com.wx.main.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reserveServiceImpl")
public class ReserveServiceImpl implements ReserveService {

    private ReserveDAO reserveDAO;

    @Autowired
    public ReserveServiceImpl(ReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }

    public List<Reserve> getReserveInfo(int sort_id) {
        return reserveDAO.getAStudentInfoBySortId(sort_id);
    }
}
