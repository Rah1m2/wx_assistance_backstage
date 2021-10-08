package com.wx.main.WebSocket;

import com.wx.main.POJO.User;
import org.springframework.stereotype.Component;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.*;

//@ServerEndpoint("/heartbeat/{userId}")
//@Component
public class HeartbeatEndPoint {
    // 通过该对象可以发送消息给指定用户
    private Session session;

    // 用户id
    private String userId;

    // 用户实体
    private User user;

    // 用于存储用户数据
    public static List<Map<String, Object>> onlineUsers = new ArrayList<Map<String, Object>>();

    @OnOpen
    // 连接建立时被调用
    public void onOpen(@PathParam("userId") String userId, Session session) {
        this.userId = userId;
        System.out.println("userId: "+userId);

        this.session = session;

        user = new User();
//        user.setUserId(userId);
//
//        // 保存对应的连接服务
//        onlineUsers.put(user, this);
    }

    @OnMessage
    // 接收到客户端发送的数据时被调用
    public void OnMessage(String message, Session session) {

        System.out.println("msg:");
        // 获取到
//        com.alibaba.fastjson.JSONObject chat = JSON.parseObject(message);
//        com.alibaba.fastjson.JSONObject message = JSON.parseObject(chat.get("message").toString());
    }

    /**
     * 向客户端发送消息
     * @param message
     * @throws IOException
     */
   public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 主动向库护短发送信息
     * @param message
     * @throws IOException
     */
    public void sendInfo() throws IOException {

//        for(HeartbeatEndPoint item : onlineUsers) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                continue;
//            }
//        }
        sendMessage("ping");
    }

    @OnClose
    // 连接断开时被调用
    public void onClose(Session session) {
        onlineUsers.remove(user);
        System.out.println("有一连接关闭");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

}
