package com.wx.main.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.main.VO.*;
import com.wx.main.Service.SearchService;
import com.wx.main.Util.Generate_TempKey_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.mail.search.SearchTerm;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
@RequestMapping("/sys")
public class SysController {

    private SearchService searchService;

    @Autowired
    public SysController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/reqKey")
    public String sendKey(String bucket, String region){
        //测试用
        System.out.println("bucket："+bucket);
        System.out.println("region："+region);
        org.json.JSONObject credential = Generate_TempKey_Util.getKey(bucket, region);
        return JSON.toJSONString(credential);
    }

    @RequestMapping(value = "/searchRes")
    public String sendSearchRes(String query, @RequestParam(value = "queryParams")String tmpQueryParams) {
        QueryParams queryParams = JSON.parseObject(tmpQueryParams, QueryParams.class);
        //待增加
        return searchService.getSearchResult(query,queryParams);
    }

    @RequestMapping(value = "/sendSubMsg")
    public String sendSubscribeMsg(SubMsgInfo subMsgInfo) {
        if (subMsgInfo.getUser_openid() == null)
            subMsgInfo.setUser_openid(searchService.getUidByAid(subMsgInfo.getArticle_id()));
        return push(subMsgInfo.getUser_openid(), subMsgInfo);
    }

    @RequestMapping(value = "/reqStatistics")
    public ResponseData sendStatistics() {
        return searchService.getStatistics();
    }

    @RequestMapping(value = "/reqPSTCountBySort")
    public ResponseData sendPSTCountGroupBySort() {
        return searchService.getPSTCountGroupBySort();
    }

    private String push(String user_openid, SubMsgInfo subMsgInfo) {
        JSONObject subMsgData = JSONObject.parseObject(subMsgInfo.getData());
        RestTemplate restTemplate = new RestTemplate();
        //这里简单起见我们每次都获取最新的access_token（时间开发中，应该在access_token快过期时再重新获取）
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + getAccessToken();
        //拼接推送的模版
        WxMssVo wxMssVo = new WxMssVo();
        wxMssVo.setTouser(user_openid);//用户的openid（要发送给那个用户，通常这里应该动态传进来的）
        wxMssVo.setTemplate_id(subMsgInfo.getTemplate_id());//订阅消息模板id
        wxMssVo.setPage("pages/index/index");

        Map<String, TemplateData> m = new HashMap<String, TemplateData>(3);
        System.out.println(subMsgData.keySet());
        Set<String> keys =  subMsgData.keySet();
        for (String key : keys) {
            m.put(key, new TemplateData((String) subMsgData.get(key)));
        }
//        m.put("thing6", new TemplateData((String) subMsgData.get("thing06")));
//        m.put("thing1", new TemplateData((String) subMsgData.get("thing01")));
//        m.put("thing2", new TemplateData((String) subMsgData.get("thing02")));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }

    private String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("APPID", "wx5184957586df2d71");
        params.put("APPSECRET", "c48e85f19194e895a7968ed3d23874c4");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={APPID}&secret={APPSECRET}", String.class, params);
        String body = responseEntity.getBody();
        com.alibaba.fastjson.JSONObject object = JSON.parseObject(body);
        String Access_Token = object.getString("access_token");
        String expires_in = object.getString("expires_in");
        System.out.println("有效时长expires_in：" + expires_in);
        return Access_Token;
    }

    /*---------------------------------测试用接口-------------------------------------*/

}