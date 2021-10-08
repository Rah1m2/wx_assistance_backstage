package com.wx.main.Controller;

import com.wx.main.POJO.Reserve;
import com.wx.main.POJO.Student;
import com.wx.main.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String receiveReserveInfo(Reserve reserve) {
        return reserveService.saveReservedInfoToRedis(reserve);
    }

    @RequestMapping(value = "/reqBacklogInfo", method = RequestMethod.POST)
    public List<Reserve> sendBacklogInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getBacklogInfo(queryForm);
    }

    @RequestMapping(value = "/reqCurReservedInfo", method = RequestMethod.POST)
    public List<Reserve> sendCurReservedInfo(@RequestBody Map<String, Object> queryForm) {
        return reserveService.getCurReservedInfo(queryForm);
    }

}
