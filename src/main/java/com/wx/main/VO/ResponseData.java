package com.wx.main.VO;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    private final String message;
    private final int status;
    private final Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

//    public void setMessage(String message) {
//        this.message = message;
//    }

    public int getStatus() {
        return status;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData setData(String key, Object value) {
        data.put(key, value);
        return this;
    }

    private ResponseData(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData elemExist() {
        return new ResponseData(841, "Inserted element is exist");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "Unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData sqlInternalException() {
        return new ResponseData(306, "SQL Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "Customer Error");
    }

    public static ResponseData tokenExpired() {
        return new ResponseData(840, "Token Expired");
    }

    public static ResponseData functionTerminated() {
        return new ResponseData(201, "Function Terminated");
    }

}