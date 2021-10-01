package com.wx.main.Service.Impl;

import com.sun.activation.registries.MailcapParseException;
import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.Reserve;
import com.wx.main.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("reserveServiceImpl")
public class ReserveServiceImpl implements ReserveService {

    private ReserveDAO reserveDAO;

    @Autowired
    public ReserveServiceImpl(ReserveDAO reserveDAO) {
        this.reserveDAO = reserveDAO;
    }

    public List<Reserve> getAStudentInfo(Map<String, Object> queryForm) {
        reserveDAO.DisableONLY_FULL_GROUP_BY();
        List<Reserve> aStuList = reserveDAO.getAStudentInfoBySortId(queryForm);
        List<Reserve> filteredList = reserveDAO.filterAStudentInfo(aStuList);
        aStuList.removeAll(filteredList);
        return aStuList;
    }

    public String saveReservedInfo(Reserve reserve) {
        return String.valueOf(reserveDAO.insertReserveInfo(reserve));
    }


}
