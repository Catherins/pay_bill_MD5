package com.swiftpass.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * <一句话功能简述>
 * <功能详细描述>配置信息
 * 
 * @author  Administrator
 * @version  [版本号, 2018-2-1]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class SwiftpassConfig {
    
    /**
     * 交易密钥
     */
    public static String key;
    
    public static String mchPrivateKey;
    
    public static String platPublicKey;
    
    /**
     * 商户号
     */
    public static String mch_id;
    
    /**
     * 请求url
     */
    public static String req_url;
    
    /**
     * 通知url
     */
    public static String notify_url;
    
    static{
        Properties prop = new Properties();   
        InputStream in = SwiftpassConfig.class.getResourceAsStream("/config.properties");   
        try {   
            prop.load(in);   
            key = prop.getProperty("key").trim();
            mchPrivateKey = prop.getProperty("mchPrivateKey").trim();
            platPublicKey = prop.getProperty("platPublicKey").trim();
            mch_id = prop.getProperty("mch_id").trim();   
            req_url = prop.getProperty("req_url").trim();   
            notify_url = prop.getProperty("notify_url").trim();   
        } catch (IOException e) {   
            e.printStackTrace();   
        } 
    }
}
