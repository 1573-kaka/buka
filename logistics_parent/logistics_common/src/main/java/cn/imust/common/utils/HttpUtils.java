package cn.imust.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

public class HttpUtils {

    public static Map<String,Object> sendGet(String url) {
        Map map = new HashMap();
        try{
            //创建HttpClient对象
            CloseableHttpClient client = HttpClients.createDefault() ;
            //创建get请求
            HttpGet get = new HttpGet(url);
            //发送get请求
            CloseableHttpResponse response = client.execute(get) ;
            HttpEntity entity = response.getEntity() ;
            String json = EntityUtils.toString(entity,"utf-8");
            System.out.println(json);
            map = JSON.parseObject(json,Map.class);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
