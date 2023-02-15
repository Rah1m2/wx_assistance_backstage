package com.wx.main.Util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class Url_Util {

    private HttpServletRequest request;

    public Url_Util(HttpServletRequest request) {
        this.request = request;
    }

    public Map<String, String> getParameters() {
        String params = request.getQueryString();
        System.out.println("params:"+params);
        String[] temp_list = params.split("&");
        Map<String, String> params_map = new HashMap<>();
        String[] temp_key_value;
        for (String s : temp_list) {
            temp_key_value = s.split("=");
            params_map.put(temp_key_value[0], temp_key_value[1]);
        }
        return params_map;
    }

    public void showUrl() {
        System.out.println("url: "+request.getScheme() +"://" + request.getServerName()
                + ":" +request.getServerPort()
                + request.getServletPath() + "?" + request.getQueryString());
    }
}
