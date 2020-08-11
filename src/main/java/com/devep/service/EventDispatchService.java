package com.devep.service;

import com.devep.mapper.FlowDispatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;

@Service
public class EventDispatchService {
    @Autowired
    FlowDispatchMapper flowDispatchMapper;
    /**
     * 保存触发用户
     * @param phoneNo
     * @param eventId
     * @return
     */
    public int saveTiggerUser(String phoneNo, String eventId, Timestamp time,String shortCode){
        return flowDispatchMapper.saveTiggerUser(phoneNo,eventId,time,shortCode);
    };
}
