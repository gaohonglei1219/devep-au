package com.devep.mapper;


import com.devep.vo.ProjectFileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectFileInfoMapper {

    int saveProjectFileInfo(ProjectFileInfo projectFileInfo);
}
