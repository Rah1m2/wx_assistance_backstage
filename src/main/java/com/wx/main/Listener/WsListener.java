package com.wx.main.Listener;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

public class WsListener implements ServletContextListener {

    private Timer timer = null;
    public void contextDestroyed(ServletContextEvent event)
    {
        timer.cancel();
        event.getServletContext().log("定时器销毁");
        System.out.println("定时器已销毁......");
    }
    public void contextInitialized(ServletContextEvent event)
    {
        //在这里初始化监听器，在tomcat启动的时候监听器启动，可以在这里实现定时器功能
        timer = new Timer(true);

        //添加日志，可在tomcat日志中查看到
        event.getServletContext().log("定时器已启动");
        System.out.println("定时器启动中......");

        //参数一是要做的事务所在类，其中必须继承TimerTask类，0表示任务立即开始无延迟，15*1000表示第一次执行后每隔15秒执行任务，60*60*1000表示一个小时；
//        timer.schedule(new SendMsg(),0,15*1000);
    }

}
