package com.devep.mapper;

import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Repository
public interface FlowDispatchMapper {
    /**
     * 保存触发用户
     * @param phoneNo
     * @param eventId
     * @return
     */
    int saveTiggerUser(@Param("phoneNo") String phoneNo,@Param("eventId") String eventId,@Param("createTime") Timestamp time,@Param("shortCode") String shortCode);
}
