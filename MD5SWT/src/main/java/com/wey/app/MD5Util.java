package com.wey.app;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
    
    public static void main(String[] args)
    {
        String encrypt = MD5Util
                .encrypt("appkeya31b4ed1-52d4-4272-ae6b-e3164b18a207paramjson{imei:\"35924901021988A\"}timestamp2018-09-21 10:10:10v1.0");
        
        System.out.println(encrypt);
    }
    
    private static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
                                             'E', 'F' };
    
    public static String encrypt(String encryptStr)
    {
        
        // 使用指定的字节更新摘要
        try
        {
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md5Instance = MessageDigest.getInstance("MD5");
            md5Instance.update(encryptStr.getBytes("UTF-8"));
            
            byte[] digest = md5Instance.digest();
            // 把密文转换成十六进制的字符串形式
            int j = digest.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = digest[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (UnsupportedEncodingException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
