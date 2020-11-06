package com.devep.service;

import com.devep.mapper.ProjectProcessMapper;
import com.devep.vo.ProjectEvent;
import com.devep.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProjectProcessService {

    @Autowired
    private ProjectProcessMapper projectProcessMapper;


    public List<ProjectEvent> getProcessEvents(String proId){
        return projectProcessMapper.getProcessEvents(proId);
    }


}
