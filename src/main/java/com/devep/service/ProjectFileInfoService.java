package com.devep.service;


import com.devep.mapper.ProjectFileInfoMapper;
import com.devep.vo.ProjectFileInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectFileInfoService {
    @Autowired
    ProjectFileInfoMapper projectFileInfoMapper;

    public boolean saveProjectFileInfo(ProjectFileInfo projectFileInfo) {
        return(projectFileInfoMapper.saveProjectFileInfo(projectFileInfo)>0?true:false);
    }
}
