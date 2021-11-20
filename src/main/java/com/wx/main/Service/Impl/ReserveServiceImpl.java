package com.wx.main.Service.Impl;

import com.wx.main.DAO.ReserveDAO;
import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.Service.ReserveService;
import com.wx.main.Util.RedisTemplate_Util;
import com.wx.main.VO.RedisCustomer;
import com.wx.main.VO.ResponseData;
import com.wx.main.VO.StudentReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

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

        //查询AStu表
        List<Student> aStuList = reserveDAO.getAStudentInfoBySortId(queryForm);

        //查询没有筛选掉过期预约的不空闲学霸
        List<Student> UnavailableList = reserveDAO.getUnavailableStudentInfo();

        //查询过期的预约条目
        List<Student> InvalidList = reserveDAO.getInvalidStudentInfo(aStuList);

        //去掉过期的预约条目
        UnavailableList.removeAll(InvalidList);

        if (UnavailableList.size() >= 2)
            aStuList.clear();
        else
            //做差集
            aStuList.removeAll(UnavailableList);



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
    public List<RedisCustomer> getBacklogInfo(Map<String, Object> queryForm) {

        String key = queryForm.get("sort_id")+":"+queryForm.get("user_openid")+":"+queryForm.get("customer_user_openid");

        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);

        Set<String> querySet = (Set<String>) RedisTemplate_Util.queryKey(key);

        List<RedisCustomer> customers = new ArrayList<>();

        for (String s : querySet) {
            customers.add((RedisCustomer) RedisTemplate_Util.get(s)) ;
        }
            System.out.println("cus:"+customers.toString());

        return customers;
    }

    @Override
    public String saveReservedInfoToDB(Reserve reserve) {
        return String.valueOf(reserveDAO.insertReserveInfo(reserve));
    }

    @Override
    public String saveReservedInfoToRedis(RedisCustomer customer) {

        RedisTemplate_Util RedisTemplate_Util = new RedisTemplate_Util(redisTemplate);

        String key = customer.getSort_id() + ":" + customer.getUser_openid() + ":" + customer.getCustomer_user_openid();

//        if (RedisTemplate_Util.get(key) != null)

//            return "EXIST";

        RedisTemplate_Util.set(key, customer);

        return "YES";
    }

    @Override
    public String processReserve(Reserve reserve, boolean isAccept) {

        //如果isAccept是true代表接受预订，反之拒绝
        if (isAccept){
            System.out.println("continuing...");
            this.saveReservedInfoToDB(reserve);
        }
        else
            System.out.println("refused continuing...");

        //导入redis
        RedisTemplate_Util RedisTemplate_util = new RedisTemplate_Util(redisTemplate);

        //生成key
        String key = reserve.getSort_id() + ":" + reserve.getUser_openid() + ":" + reserve.getCustomer_user_openid();

        //无论拒绝还是同意，都会删除redis中的键值对
        RedisTemplate_util.delete(key);

        return "YES";
    }

    @Override
    public List<StudentReserve> getCurAcceptedREZInfo(Map<String, Object> queryForm) {
        return reserveDAO.getCurAcceptedREZInfoByUserOpenid(queryForm);
    }

    @Override
    public Map<String, Object> getReservationInfoOfCur(String user_openid) {

        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);

        String query = "*" + ":*:" + user_openid;

        Set<String> keySets = (Set<String>) redisTemplate_util.queryKey(query);

        List<RedisCustomer> customers = new ArrayList<>();

        for (String keySet : keySets) {
            RedisCustomer redisCustomer = (RedisCustomer) redisTemplate_util.get(keySet);
            customers.add(redisCustomer);
        }

        //封装
        Map<String, Object> map = new HashMap<>();
        map.put("redisData", customers);
        map.put("mysqlData", reserveDAO.getREZInfoOfCurByUserOpenid(user_openid));

        return map;
    }

    @Override
    public ResponseData endProcReservation(Map<String, Object> disableForm) {
//        if (reserveDAO.delRezByMissionId(mission_id) == 0)
//            return ResponseData.notFound();
//        return ResponseData.ok();
        if (reserveDAO.updateRezDeadlineByMissionId(disableForm) == 0)
            return ResponseData.sqlInternalException();
        return ResponseData.ok();
    }

    @Override
    public ResponseData getCurContactDetail(String user_openid) {
        return ResponseData.ok().setData("contact_detail", reserveDAO.getContactDetailByUserOpenid(user_openid));
    }

    @Override
    public ResponseData isRedisContainAStu(String user_openid) {

        RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);

        Set<String> keySet = (Set<String>) redisTemplate_util.queryKey("*:oRjTi4jAwOlEfmsQ1M2AS2D95pYY:*");

        if (keySet.isEmpty())
            return ResponseData
                    .ok();
        else
            return ResponseData
                    .forbidden();
    }

}
