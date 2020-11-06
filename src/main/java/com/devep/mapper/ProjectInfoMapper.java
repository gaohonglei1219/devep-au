package com.devep.mapper;


import com.devep.vo.ProjectInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectInfoMapper {

    int saveProjectInfo(ProjectInfo projectInfo);

    int getProjectNumberByProMail(String proMail);

    List<ProjectInfo> getProjectInfosByProMail(@Param("proMail") String proMail);
}
