package com.wx.main.Controller;

import com.wx.main.POJO.Reserve;
import com.wx.main.Service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/reserve")
public class ReserveController {
    private ReserveService reserveService;

    @Autowired
    public ReserveController(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @RequestMapping(value = "/reqAStudentsInfo")
    public List<Reserve> sendAStudentsInfo(int sort_id) {
        return reserveService.getReserveInfo(sort_id);
    }
}
