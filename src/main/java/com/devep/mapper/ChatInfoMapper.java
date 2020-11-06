package com.devep.mapper;

import com.devep.vo.ChatVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatInfoMapper {

    int saveChatInfo(ChatVo chatVo);

    public List<ChatVo> getChatInfoByProId(String proId);
}
