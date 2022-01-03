package com.wx.main.Util;

import net.sf.ehcache.util.PropertyUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Desc:properties文件获取工具类
 * Created by hafiz.zhang on 2016/9/15.
 */
public class Property_Util {

    private static Properties props;
    private static String propName;

//    static{
//        loadProps();
//    }


    public static void setPropName(String propName) {
        Property_Util.propName = propName;
    }

    synchronized static private void loadProps(){
        props = new Properties();
        InputStream in = null;
        try {
            in = PropertyUtil.class.getClassLoader().getResourceAsStream(propName);
            props.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != in) {
                    in.close();
                }
            } catch (IOException e) {
                System.out.println("jdbc.properties文件流关闭出现异常");
            }
        }
    }

    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        key = props.getProperty(key);
        props = null;
        return key;
    }
}
