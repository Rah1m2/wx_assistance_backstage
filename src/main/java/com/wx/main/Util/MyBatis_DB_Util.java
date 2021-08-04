package com.wx.main.Util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mortbay.resource.Resource;
import java.io.InputStream;

public class MyBatis_DB_Util {

    private static SqlSessionFactory sqlSessionFactory;

    static{
        String resource = "/mybatis-config.xml";
        InputStream inputStream = Resource.class.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static SqlSession getSqlSession(){

        //从工厂中获取SqlSession对象（相当于原来的statement查询对象）
        return sqlSessionFactory.openSession();
    }
}
