package com.devep.service;

import com.devep.mapper.ChatInfoMapper;
import com.devep.vo.ChatVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatInfoServer {

    @Autowired
    private ChatInfoMapper chatInfoMapper;

    public boolean saveChatInfo(ChatVo chatVo){
        return(chatInfoMapper.saveChatInfo(chatVo)>0?true:false);
    }

    public List<ChatVo> getChatInfoByProId(String proId){
        return chatInfoMapper.getChatInfoByProId(proId);
    }
}
