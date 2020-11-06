package com.devep.service;


import com.devep.mapper.ProjectInfoMapper;
import com.devep.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectInfoService {
    @Autowired
    private ProjectInfoMapper projectInfoMapper;

    public boolean saveProjectInfo(ProjectInfo projectInfo) {
        return(projectInfoMapper.saveProjectInfo(projectInfo)>0?true:false);
    }

    public int getProjectNumberByProMail(String proMail) {
        return projectInfoMapper.getProjectNumberByProMail(proMail);
    }

    public List<ProjectInfo> getProjectInfosByProMail(String proMail) {
        return  projectInfoMapper.getProjectInfosByProMail(proMail);
    }
}
