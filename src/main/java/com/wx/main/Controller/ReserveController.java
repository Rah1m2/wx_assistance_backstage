package com.wx.main.Controller;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.Service.ReserveService;
import com.wx.main.VO.RedisCustomer;
import com.wx.main.VO.ResponseData;
import com.wx.main.VO.StudentReserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/reserve")
public class ReserveController {
    private ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @RequestMapping(value = "/reqAStudentsInfo", method = RequestMethod.POST)
    public List<Student> sendAStudentsInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getAStudentInfoFromDB(queryForm);
    }

    @RequestMapping(value = "/sendReserveInfo")
    public String receiveReserveInfo(RedisCustomer customer) {
        return reserveService.saveReservedInfoToRedis(customer);
    }

    @RequestMapping(value = "/reqBacklogInfo", method = RequestMethod.POST)
    public List<RedisCustomer> sendBacklogInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getBacklogInfo(queryForm);
    }

    @RequestMapping(value = "/reqCurReservedInfo", method = RequestMethod.POST)
    public List<StudentReserve> sendCurAcceptedREZInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getCurAcceptedREZInfo(queryForm);
    }

    @RequestMapping(value = "/reqCurBacklogInfo", method = RequestMethod.POST)
    public List<RedisCustomer> sendCurBacklogInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getBacklogInfo(queryForm);
    }

    @RequestMapping(value = "/processReserve")
    public String processReserve(Reserve reserve, boolean isAccept) {
        return reserveService.processReserve(reserve, isAccept);
    }

    @RequestMapping(value = "/reqReservationInfoOfCur")
    public Map<String, Object> sendReservationOfCur(String user_openid) {
        return reserveService.getReservationInfoOfCur(user_openid);
    }

    @RequestMapping(value = "/delReservation")
    public ResponseData delReservation(int mission_id) {
        return reserveService.endProcReservation(mission_id);
    }

    @RequestMapping(value = "/reqCurNumber")
    public ResponseData sendCurContactDetail(String user_openid) {
        return reserveService.getCurContactDetail(user_openid);
    }
}
