package com.swiftpass.action;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.swiftpass.config.SwiftpassConfig;
import com.swiftpass.util.MD5;
import com.swiftpass.util.SignUtil;
import com.swiftpass.util.SignUtils;
import com.swiftpass.util.XmlUtils;

/**
 * <一句话功能简述>
 * <功能详细描述>下载对账单
 * 
 * @author  Administrator
 * @version  [版本号, 2018-2-01]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class TestBillDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        
        SortedMap<String,String> map = XmlUtils.getParameterMap(req);
        
        	map.put("mch_id", SwiftpassConfig.mch_id);
            map.put("nonce_str", String.valueOf(new Date().getTime()));
            Map<String,String> params = SignUtils.paraFilter(map);
            StringBuilder buf = new StringBuilder((params.size() +1) * 10);
            SignUtils.buildPayParams(buf,params,false);
            String preStr = buf.toString();
            String sign_type = map.get("sign_type");
            map.put("sign", SignUtil.getSign(sign_type, preStr));
            
            String reqUrl = "https://download.swiftpass.cn/gateway";

            
            System.out.println("reqParams:" + XmlUtils.parseXML(map));
            CloseableHttpResponse response = null;
            CloseableHttpClient client = null;
            String res = null;
            try {
                HttpPost httpPost = new HttpPost(reqUrl);
                StringEntity entityParams = new StringEntity(XmlUtils.parseXML(map),"utf-8");
                httpPost.setEntity(entityParams);
                httpPost.setHeader("Content-Type", "text/xml;charset=utf-8");
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                if(response != null && response.getEntity() != null){
                	
                	res = new String(EntityUtils.toByteArray(response.getEntity()),"utf-8");
                    
                }else{
                    res = "操作失败!";
                }
            } catch (Exception e) {
                e.printStackTrace();
                res = "系统异常";
            } finally {
                if(response != null){
                    response.close();
                }
                if(client != null){
                    client.close();
                }
            }
            resp.setContentType("text/html; charset=UTF-8");
            resp.getWriter().print(res);
        
    }
}
