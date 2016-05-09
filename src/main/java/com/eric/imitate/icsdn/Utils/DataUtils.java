package com.eric.imitate.icsdn.Utils;

import android.util.Log;

import com.eric.imitate.icsdn.Bean.CommonException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/11/16.
 */
public class DataUtils{
    public static String doGet(String urlStr) throws CommonException{
        Log.d("TAG", "urlStr = " + urlStr);
        StringBuffer stringBuffer = new StringBuffer();
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            Log.d("TAG", "conn =" + conn);
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.0");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
            conn.setRequestProperty("Content-Type", "text/html;charset=utf-8");
//            conn.setRequestProperty("Accept-Charset", "utf-8");
//            conn.setRequestProperty("contentType", "utf-8");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            if(conn.getResponseCode() == 200){
                InputStream inputStream = conn.getInputStream();
//                int len = 0;
//                byte[] buf = new byte[1024];
//
//                while((len = inputStream.read()) != -1){
//                    stringBuffer.append(new String(buf, 0, len, "utf-8"));
//                }
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
                String line = null;
                while((line = br.readLine()) != null){
                    stringBuffer.append(line);
                }
                inputStream.close();
            }else{
                throw new CommonException("访问网络失败");
            }
        } catch (Exception e) {
            throw new CommonException("访问网络失败");
        }

        Log.d("TAGttt", "stringBuffer" + stringBuffer.substring(10400));
        return stringBuffer.toString();
    }
}
