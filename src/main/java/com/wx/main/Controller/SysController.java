package com.wx.main.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wx.main.VO.QueryParams;
import com.wx.main.VO.SubMsgInfo;
import com.wx.main.VO.TemplateData;
import com.wx.main.VO.WxMssVo;
import com.wx.main.Service.SearchService;
import com.wx.main.Util.Generate_TempKey_Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/sys")
public class SysController {

    private SearchService searchService;



    @Autowired
    public SysController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/reqKey")
    @ResponseBody
    public String sendKey(String bucket, String region){
        //测试用
        System.out.println("bucket："+bucket);
        System.out.println("region："+region);
        org.json.JSONObject credential = Generate_TempKey_Util.getKey(bucket, region);
        return JSON.toJSONString(credential);
    }

    @RequestMapping(value = "/searchRes")
    @ResponseBody
    public String sendSearchRes(String query, @RequestParam(value = "queryParams")String tmpQueryParams) {
        QueryParams queryParams = JSON.parseObject(tmpQueryParams, QueryParams.class);
        //待增加
        return searchService.getSearchResult(query,queryParams);
    }

    @RequestMapping(value = "/sendSubMsg")
    @ResponseBody
    public String sendSubscribeMsg(SubMsgInfo subMsgInfo) {
        String user_openid = searchService.getUidByAid(subMsgInfo.getArticle_id());
        return push(user_openid, subMsgInfo);
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
        m.put("name4", new TemplateData((String) subMsgData.get("name04")));
        m.put("thing1", new TemplateData((String) subMsgData.get("thing01")));
        m.put("thing2", new TemplateData((String) subMsgData.get("thing02")));
        wxMssVo.setData(m);
        ResponseEntity<String> responseEntity =
                restTemplate.postForEntity(url, wxMssVo, String.class);
        return responseEntity.getBody();
    }

    private String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("APPID", "wx8b35bb94f164b782");
        params.put("APPSECRET", "6896ae1a015096e1704fb01cc5ef6d3b");
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