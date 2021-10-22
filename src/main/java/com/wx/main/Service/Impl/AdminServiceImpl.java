package com.wx.main.Service.Impl;

import com.wx.main.DAO.AdminDAO;
import com.wx.main.DAO.PostingDAO;
import com.wx.main.POJO.Sort;
import com.wx.main.POJO.User;
import com.wx.main.Service.AdminService;
import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.VO.RedisCustomer;
import com.wx.main.VO.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service("adminServiceImpl")
public class AdminServiceImpl implements AdminService {

    private AdminDAO adminDAO;
    private PostingDAO postingDAO;
    private RedisTemplate redisTemplate;

    @Autowired
    public AdminServiceImpl(AdminDAO adminDAO, PostingDAO postingDAO, RedisTemplate redisTemplate) {
        this.adminDAO = adminDAO;
        this.postingDAO = postingDAO;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public ResponseData GarryKing(Map<String, Object> userForm) {

        if (userForm.get("user_status") == null && userForm.get("user_identity") != null) {
            adminDAO.deleteAStuByQuery(userForm);
            return ResponseData.ok();
        }

        if (userForm.get("user_status") != null && userForm.get("user_identity") != null) {
            if (!(Boolean) userForm.get("user_status"))
                adminDAO.unbarAccount(userForm);
            else
                adminDAO.barAccount(userForm);
            adminDAO.deleteAStuByQuery(userForm);
        }

        if (userForm.get("user_status") == null && userForm.get("user_identity") != null)
            adminDAO.deleteAStuByQuery(userForm);

        if (userForm.get("user_status") != null && userForm.get("user_identity") == null) {
            if (!(Boolean) userForm.get("user_status"))
                adminDAO.unbarAccount(userForm);
            else
                adminDAO.barAccount(userForm);
        }

        return ResponseData.ok();
    }

    @Override
    public boolean verifyLogin(Map<String, Object> loginForm) {
        Map<String, Object> result = adminDAO.getAdminByAcPw(loginForm);
        return result != null && result.size() == 2;
    }

    @Override
    public ResponseData register(Map<String, Object> registerForm) {

        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);

        String non_validated_code = (String) registerForm.get("verification_code");

        String key = "verification:" + non_validated_code;

        Object code_is_valid =  redisTemplate_util.get(key);

        if (code_is_valid == null || !((boolean) code_is_valid))
            return ResponseData.unauthorized();

        redisTemplate_util.delete(key);

        int result = adminDAO.insertAdmin(registerForm);

        if (result == 0)
            return ResponseData.unauthorized();

        return ResponseData.ok();
    }

    @Override
    public ResponseData getUserInfo() {

        //封禁数据
        List<User> bannedList = adminDAO.getUserByQuery("Banned");

        //学霸数据
        List<User> aStuList = adminDAO.getUserByQuery("AStu");

        //原始数据
        List<User> rawList = adminDAO.getUserByQuery("ALL");

        //学渣数据
//        rawList.removeAll(aStuList);

        return ResponseData
                .ok()
                .setData("rawList", rawList);
    }

    @Override
    public ResponseData getAStuInfo() {
        //学霸数据
        List<User> aStuList = adminDAO.getUserByQuery("AStu");
        return ResponseData
                .ok()
                .setData("aStuList", aStuList);
    }

    @Override
    public ResponseData getBannedInfo() {

        //封禁数据
        List<User> bannedList = adminDAO.getUserByQuery("Banned");

        return ResponseData
                .ok()
                .setData("bannedList", bannedList);
    }

    @Override
    public ResponseData getUnconfirmedREZ() {

        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);

        String key = "?:*";

        Set<String> querySet = (Set<String>) redisTemplate_util.queryKey(key);

        List<RedisCustomer> redisCustomers = new ArrayList<>();

        for (String s : querySet)
            redisCustomers.add((RedisCustomer) redisTemplate_util.get(s));

        return ResponseData
                .ok()
                .setData("Reservations", redisCustomers);
    }

    @Override
    public ResponseData delUnconfirmedREZ() {

        return null;
    }

    @Override
    public ResponseData getAcceptedREZ() {
        return ResponseData
                .ok()
                .setData("Reservations", adminDAO.getReservations());
    }

    @Override
    public ResponseData delAcceptedREZ(int mission_id) {
        adminDAO.deleteReservation(mission_id);
        return ResponseData
                .ok();
    }

    @Override
    public ResponseData editSortName(Map<String, Object> sortForm) {
        adminDAO.updateSort(sortForm);
        return ResponseData
                .ok();
    }

    @Override
    public ResponseData delSort(int sort_id) {
        adminDAO.deleteSort(sort_id);
        return ResponseData.ok();
    }

    @Override
    public ResponseData addSort(String addSortName) {

        List<Sort> sorts = postingDAO.getSortInfo();

        for (Sort sort : sorts) {
            if (sort.getSort_name().equals(addSortName))
                return ResponseData
                        .elemExist();
        }

        if (adminDAO.insertSort(addSortName) == 0)
            return ResponseData
                    .sqlInternalException();

        return ResponseData
                .ok();
    }

}
