package com.devep.controller;

import com.devep.service.ProjectInfoService;
import com.devep.service.ProjectProcessService;

import com.devep.vo.ProjectEvent;
import com.devep.vo.ProjectInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@RequestMapping("/projectProcess")
public class ProjectProcessController {

    @Autowired
    private ProjectProcessService projectProcessService;
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping(value = "/getProcessInfo",method = RequestMethod.GET)
    @ResponseBody
    public List<ProjectEvent> getProjectEvents(@RequestParam("proId") String proId){
        List<ProjectEvent> events = projectProcessService.getProcessEvents(proId);
        return events;
    }

    @RequestMapping(value = "/getProcessList",method = RequestMethod.POST)
    @ResponseBody
    public List<ProjectInfo> getProjectInfosByProMail(@RequestParam("proMail") String proMail){
        List<ProjectInfo> projects = projectInfoService.getProjectInfosByProMail(proMail);

        return projects;
    }
}
