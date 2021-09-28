package com.wx.main;

import com.alibaba.fastjson.JSONObject;
import com.wx.main.DAO.SearchDAO;
import com.wx.main.POJO.Posting;
import com.wx.main.Service.PostingService;
import com.wx.main.Util.Transcoding_Util;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester();
	}

	@Test
	public void dateTest() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}

	@Test
	public void testRenderMyPage() {
		String config = "/spring/applicationContext.xml";

		ApplicationContext acContext = new ClassPathXmlApplicationContext(config);

		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) acContext.getBean("sqlSessionFactory");
		//通过接口调用方法执行查询
		PostingService postingService = (PostingService) acContext.getBean("postingServiceImpl");

//		System.out.println(postingService.getRequiredPostings("test"));
	}

	@Test
	public void DAOTest()
	{
		String config = "/spring/applicationContext.xml";

		ApplicationContext acContext = new ClassPathXmlApplicationContext(config);

		SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) acContext.getBean("sqlSessionFactory");
		//通过接口调用方法执行查询
//		UserService userService = (UserService) acContext.getBean("PostingServiceImpl");

//		System.out.println(userService.getUserList());

		SqlSession sqlSession = sqlSessionFactory.openSession();

		SearchDAO searchDAO = sqlSession.getMapper(SearchDAO.class);

		List<Posting> entity =  searchDAO.dynamicSearch("哥哥");

		System.out.println(entity.toString());


//		//遍历打印
//		for (User user : list) {
//			System.out.println("my account:"+user.getUser_account());
//		}
//
//		//带限制条件的查询
//		System.out.println(userMapper.getUserByAccount("772557832@qq.com"));
//
//		//插入操作
//		int res = userMapper.insertSingleUser(new User(2,"1962219910@qq.com","010501","psychology",509,"Sichuan China","female","second"));
//
//		//这里很重要，操作完成后必须提交事务
//		if(res >= 1){
//			System.out.println("插入成功！");
//			sqlSession.commit(); //提交事务之后才会写入数据库
//		}
//
//		//删除操作
//		res = userMapper.deleteSingleUser(new User(65535,"1962219910@qq.com",null,null,65535,null,null,null));
//		if(res >= 1){
//			System.out.println("删除成功！");
//			sqlSession.commit(); //提交事务之后才会写入数据库
//		}
//
//		//修改操作
//		res = userMapper.updateSingleUser(new User(65535,"772557832@qq.com","00000000",null,65535,null,null,null));
//		if(res >= 1){
//			System.out.println("修改成功！");
//			sqlSession.commit(); //提交事务之后才会写入数据库
//		}

		//关闭连接以免出现内存问题
//		sqlSession.close();
	}

	@Test
	public void fuck(){
		String config = "/spring/applicationContext.xml";

		ApplicationContext acContext = new ClassPathXmlApplicationContext(config);


		String[] Slist = acContext.getBeanDefinitionNames();

		for (String s : Slist) {
			System.out.println(s);
		}
	}
	@Test
	public void Util_Test(){
		String config = "/spring/applicationContext.xml";

		ApplicationContext acContext = new ClassPathXmlApplicationContext(config);

//		acContext.getBean("Transcoding_Util");
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("pageNum",1);

		//查询的页数
		jsonObject.put("pageSize",2);
		jsonObject.put("ZH","中国第一");
		Transcoding_Util.Transcode(jsonObject);
	}
}


