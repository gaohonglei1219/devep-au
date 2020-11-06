package com.devep.controller;


import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.alibaba.fastjson.JSONObject;
import com.devep.entity.OfflineChatInfo;
import com.devep.service.ChatInfoServer;
import com.devep.utils.RedisUtil;
import com.devep.vo.ChatVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/imserver/{userMail}")
@Component
public class WebSocketServer {
    private static int ExpireTime = 60;
    static Log log= LogFactory.get(WebSocketServer.class);
    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/

    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**用户邮箱地址*/
    private String userMail = "";
    @Autowired
    private ChatInfoServer chatInfoServer;
    @Autowired
    private RedisUtil redisUtil;



    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(@PathParam("userMail") String userMail,Session session ) {
        this.session = session;
        this.userMail= userMail;
        if(webSocketMap.containsKey(userMail)){
            webSocketMap.remove(userMail);
            webSocketMap.put(userMail,this);
            //加入set中
        }else{
            webSocketMap.put(userMail,this);
        }

//        try {
//            //sendMessage("连接成功");
//        } catch (IOException e) {
//            log.error("用户:"+userMail+",网络异常!!!!!!");
//        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userMail)){
            webSocketMap.remove(userMail);
        }
        log.info("用户退出:"+userMail);
    }

    /**
     * 消息处理
     */
    @OnMessage
    public void onMessage(String message, Session session,@PathParam("userMail") String userMail){
        ChatVo chatVo;
        ObjectMapper objectMapper = new ObjectMapper();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", JSONObject.parseObject(message).get("code"));
        try{
            chatVo = objectMapper.readValue(message, ChatVo.class);
            jsonObject.put("userMail", userMail + "：");

            chatVo.setFrom(userMail);
            Session fromSession = webSocketMap.get(chatVo.getFrom()).session;
            WebSocketServer ws = webSocketMap.get(chatVo.getTo());


            //接受者存在,发送以下消息给接受者和发送者
            if(ws != null){
                Session toSession = ws.session;
                jsonObject.put("msg", chatVo.getContent());
                jsonObject.put("sendTime", chatVo.getSendTime());

                fromSession.getAsyncRemote().sendText(jsonObject.toJSONString());
                toSession.getAsyncRemote().sendText(jsonObject.toJSONString());
                //发送成功之后存储数据

            }else{
                //发送者不存在,转存为离线消息
//                jsonObject.put("msg", "频道号不存在或对方不在线");
                jsonObject.put("msg",chatVo.getContent());
                jsonObject.put("sendTime",chatVo.getSendTime());
                fromSession.getAsyncRemote().sendText(jsonObject.toJSONString());
                List<Object> offlineChatInfoList = redisUtil.lGet(chatVo.getTo(),0,-1);
                //如果没有离线消息，创建
                if(null == offlineChatInfoList){
                    offlineChatInfoList = new ArrayList<>();
                }
                offlineChatInfoList.add(jsonObject);
                redisUtil.lSet(chatVo.getTo(),offlineChatInfoList);
            }
        }catch (Exception e){
            log.error("发送消息出错");
            e.printStackTrace();
        }
    }

    /**
     * 关闭连接
     */

}
