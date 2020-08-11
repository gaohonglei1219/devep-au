package com.devep.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StreamUtils;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class SyncUtil {
    private static final Logger logger = LogManager.getLogger("sysLog");

    public static String getOuterOrderID()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddhhMMss");//显示20171027100000格式

        Random random = new Random();

        String result = "";

        for (int i = 0; i < 8; i++)
        {
            result += random.nextInt(10);
        }

        return sdf.format(new Date()) + result;
    }

    public static String transfCity(String cityId)
    {
        String newCityId = "";

        if("10".equals(cityId))
        {
            newCityId = "0827";
        }
        else if("11".equals(cityId))
        {
            newCityId = "0825";
        }
        else if("12".equals(cityId))
        {
            newCityId = "0831";
        }
        else if("13".equals(cityId) || "14".equals(cityId))
        {
            newCityId = "0832";
        }
        else if("15".equals(cityId) || "19".equals(cityId))
        {
            newCityId = "0833";
        }
        else if("16".equals(cityId))
        {
            newCityId = "0835";
        }
        else if("17".equals(cityId))
        {
            newCityId = "0838";
        }
        else if("18".equals(cityId))
        {
            newCityId = "0817";
        }
        else if("2".equals(cityId))
        {
            newCityId = "028";
        }
        else if("20".equals(cityId))
        {
            newCityId = "0837";
        }
        else if("21".equals(cityId))
        {
            newCityId = "0836";
        }
        else if("3".equals(cityId))
        {
            newCityId = "0816";
        }
        else if("4".equals(cityId))
        {
            newCityId = "0813";
        }
        else if("5".equals(cityId))
        {
            newCityId = "0812";
        }
        else if("6".equals(cityId))
        {
            newCityId = "0839";
        }
        else if("6".equals(cityId))
        {
            newCityId = "0839";
        }
        else if("7".equals(cityId))
        {
            newCityId = "0818";
        }
        else if("8".equals(cityId))
        {
            newCityId = "0830";
        }
        else if("9".equals(cityId))
        {
            newCityId = "0826";
        }
        else if("87".equals(cityId))
        {
            newCityId = "0840";
        }

        return newCityId;
    }
    public static String sendJsonToUrl(String jsonString,String url)  {
        logger.info("10086入参：" + jsonString);
        String res;//发送结果
        try{
            //	调用10086接口
            URL restURL = new URL(url);

            HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");   //发送json数据

            conn.setRequestProperty("Content-Length", String.valueOf(jsonString.length()));

            conn.setRequestProperty("X-Channel-Id", "IOP_SC");

            String transId = "IOP_SC" + getOuterOrderID();

            conn.setRequestProperty("X-Trans-Id", transId);

            conn.setDoOutput(true);

            OutputStream out = conn.getOutputStream();

            out.write(jsonString.getBytes("utf-8"));

            out.flush();

            out.close();

            String resBody = StreamUtils.copyToString(conn.getInputStream(), Charset.forName("utf-8"));   //获取10086接口的响应
            //logger.info("10086接口响应resBody" + resBody);
            JSONObject jsonRes = (JSONObject) JSONObject.parse(resBody);

            logger.info("10086接口响应resBody" + jsonRes);

            logger.info("object rntCode =" + "0".equals((String)jsonRes.getJSONObject("object").get("rtnCode")));
            res = (String) jsonRes.getJSONObject("object").get("rtnCode");
         }
		    catch (Exception e)
        {
            e.printStackTrace();
            res = "-2";
        }
        return res;
    }
}
