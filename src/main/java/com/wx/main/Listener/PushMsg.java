//package com.wx.main.Listener;
//
//import java.io.IOException;
//import java.util.TimerTask;
//
//public class PushMsg extends TimerTask {
//
//    private com.gaokao.main.WebSocket.HeartbeatEndPoint heartbeatEndPoint;
//
//    public PushMsg(com.gaokao.main.WebSocket.HeartbeatEndPoint heartbeatEndPoint) {
//        this.heartbeatEndPoint = heartbeatEndPoint;
//    }
//
//    public void run() {
//        System.out.println("tips: thread processing...");
//        try {
//            heartbeatEndPoint.sendInfo();
//        } catch (IOException e) {
//            System.out.println("信息发送异常。");
//            e.printStackTrace();
//        }
//    }
//
//}
//
