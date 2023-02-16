package com.wx.main;

import com.wx.main.Algorithm.Recommend;
import com.wx.main.Util.RedisTemplate_Util;
import org.junit.Test;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

@MapperScan("com.wx.main.*")
public class Start {

	private RedisTemplate redisTemplate;

	public Start() {
	}

	@Test
	public void test() throws Exception {
		String config = "/spring/applicationContext.xml";
		ApplicationContext acContext = new ClassPathXmlApplicationContext(config);
		redisTemplate = (RedisTemplate) acContext.getBean("redisTemplate");
		RedisTemplate_Util redisTemplate_util = new RedisTemplate_Util(redisTemplate);
		redisTemplate_util.get("db_graduate_design:user_vector:oRSGA5ED1yYb4CUbswh_j7VkNXNk:55");
		Recommend recommend = new Recommend(redisTemplate);
		recommend.getUserVectorMatrix("1234css");
	}
}
