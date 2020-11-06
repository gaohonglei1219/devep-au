package com.devep.mapper;


import com.devep.vo.ProjectEvent;
import com.devep.vo.ProjectInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectProcessMapper {


    List<ProjectEvent> getProcessEvents(@Param("proId") String proId);


}
