package com.wx.main.Service.Impl;

import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.Service.ReserveService;
import com.wx.main.Util.RedisTemplate_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
@Service("reserveServiceImpl")
public class ReserveServiceImpl implements ReserveService {

    private ReserveDAO reserveDAO;
    private RedisTemplate redisTemplate;

    @Autowired
    public ReserveServiceImpl(ReserveDAO reserveDAO, RedisTemplate redisTemplate) {
        this.reserveDAO = reserveDAO;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List<Student> getAStudentInfoFromDB(Map<String, Object> queryForm) {

        //暂时关闭ONLY_FULL_GROUP_BY
        reserveDAO.DisableONLY_FULL_GROUP_BY();

        //查询学霸表
        List<Student> aStuList = reserveDAO.getAStudentInfoBySortId(queryForm);

        //查询预约表
        List<Student> filteredList = reserveDAO.filterAStudentInfo(aStuList);

        //做差集
        aStuList.removeAll(filteredList);

//        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);
//
//        String key = queryForm.get("sort_id")+":*:"+queryForm.get("user_openid");
//
//        Set<String> querySet = (Set<String>) RedisTemplate_Util.queryKey(key);
//
//        List<Reserve> reserves = new ArrayList<>();
//
//        for (String s : querySet) {
//            reserves.add((Reserve) RedisTemplate_Util.get(s)) ;
//        }
//
//        if (!reserves.isEmpty())
//            aStuList.removeAll(reserves);

        return aStuList;
    }

    @Override
    public List<Reserve> getBacklogInfo(Map<String, Object> queryForm) {

        String key = queryForm.get("sort_id")+":*:"+queryForm.get("user_openid");

        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);

        Set<String> querySet = (Set<String>) RedisTemplate_Util.queryKey(key);

        List<Reserve> reserves = new ArrayList<>();

        for (String s : querySet) {
            reserves.add((Reserve) RedisTemplate_Util.get(s)) ;
        }

        return reserves;
    }

    @Override
    public String saveReservedInfoToDB(Reserve reserve) {

        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);

        String key = reserve.getSort_id() + ":" + reserve.getUser_openid() + ":" + reserve.getCustomer_user_openid();

//        if (RedisTemplate_Util.get(key) != null)
//            return "EXIST";

        RedisTemplate_Util.set(key, reserve);

        return String.valueOf(reserveDAO.insertReserveInfo(reserve));
    }

    @Override
    public String saveReservedInfoToRedis(Reserve reserve) {

        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);

        String key = reserve.getSort_id() + ":" + reserve.getUser_openid() + ":" + reserve.getCustomer_user_openid();

//        if (RedisTemplate_Util.get(key) != null)
//            return "EXIST";

        RedisTemplate_Util.set(key, reserve);

        return "YES";
    }

    @Override
    public String processReserve(Reserve reserve) {

        return null;
    }

    @Override
    public List<Reserve> getCurReservedInfo(Map<String, Object> queryForm) {
        return reserveDAO.getCurReservedInfoByUserOpenid(queryForm);
    }

}
