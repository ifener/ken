package com.wey.app;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class HttpClientUtil
{
    
    public static String doGet(String url)
    {
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        
        HttpResponse httpRes = null;
        try
        {
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
        catch (UnsupportedOperationException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                httpClient.close();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            httpClient = null;
            httpRes = null;
            get = null;
        }
        return null;
    }
}
