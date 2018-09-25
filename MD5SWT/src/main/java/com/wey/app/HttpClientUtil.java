package com.wey.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
    
    public static String doGet(String url) {
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        
        HttpResponse httpRes = null;
        try {
            httpRes = httpClient.execute(get);
            
            HttpEntity entity = httpRes.getEntity();
            /*BufferedReader reader = null;
            reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
            
            StringBuffer retStr = new StringBuffer();
            String str = "";
            while (StringUtils.isNotEmpty(str = reader.readLine()))
            {
                retStr.append(str);
            }
            str = new String(retStr.toString().getBytes(), "UTF-8");
            return str;*/
            
            String resStr = EntityUtils.toString(entity, "utf-8");
            return resStr;
        }
        catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            httpClient = null;
            httpRes = null;
            get = null;
        }
        return null;
    }
    
    public static String doPost(String url, Map<String, String> params) {
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        
        HttpResponse httpRes = null;
        try {
            
            // 创建post方式请求对象
            HttpPost httpPost = new HttpPost(url);
            
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            if (params != null) {
                for (Entry<String, String> entry : params.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
            }
            
            // 设置参数到请求对象中
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
            
            // 执行请求操作，并拿到结果（同步阻塞）
            httpRes = httpClient.execute(httpPost);
            // 获取结果实体
            HttpEntity entity = httpRes.getEntity();
            
            String resStr = EntityUtils.toString(entity, "utf-8");
            return resStr;
        }
        catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                httpClient.close();
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            httpClient = null;
            httpRes = null;
        }
        return null;
    }
}
