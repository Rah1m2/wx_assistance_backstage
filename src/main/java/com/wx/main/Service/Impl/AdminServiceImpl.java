package com.wx.main.Service.Impl;

import com.github.pagehelper.PageHelper;
import com.sun.tools.corba.se.idl.InterfaceGen;
import com.wx.main.DAO.AdminDAO;
import com.wx.main.POJO.User;
import com.wx.main.Service.AdminService;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    @Override
    public boolean verifyLogin(Map<String, Object> loginForm) {
        Map<String, Object> result = adminDAO.getAdminByAcPw(loginForm);
        if (result == null || result.size() != 2)
            return false;
        return true;
    }

    @Override
    public ResponseData getUserInfo(Map<String, Integer> queryForm) {

        PageHelper.startPage(queryForm.get("pageNum"), queryForm.get("pageSize"));

        //封禁数据
        List<User> bannedList = adminDAO.getUserByQuery("Banned");

        //学霸数据
        List<User> aStuList = adminDAO.getUserByQuery("AStu");

        //原始数据
        List<User> pStuList = adminDAO.getUserByQuery("ALL");

        //学渣数据
        pStuList.removeAll(aStuList);

        return ResponseData
                .ok()
                .setData("bannedList", bannedList)
                .setData("aStuList", aStuList)
                .setData("pStuList", pStuList);
    }
}
