package com.devep.controller;

import com.devep.service.ProjectFileInfoService;
import com.devep.service.ProjectInfoService;
import com.devep.service.UserInfoService;
import com.devep.vo.ProjectFileInfo;
import com.devep.vo.ProjectInfo;
import com.devep.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@Controller
@CrossOrigin
@RequestMapping("/indexPro")
public class IndexController {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private ProjectInfoService projectInfoService;
    @Autowired
    private ProjectFileInfoService projectFileInfoService;

    @RequestMapping("/projectSubmit")
    @ResponseBody
    public String projectSubmit(MultipartFile file, ProjectInfo projectInfo, UserInfo userInfo){

        //判断是否新用户，保存用户信息
        userInfo.setUserMail(projectInfo.getProMail());
        Boolean isUserExist = userInfoService.getUserMailExists(userInfo.getUserMail());
        //设置用户邮箱

        if(isUserExist){
            //老用户仅录入项目信息
        }else{
            //新用户录入新用户信息

            boolean sucUser = userInfoService.saveUserInfo(userInfo);
            //录入项目信息
            if(!sucUser){
                return "false";
            }

        }


        //保存文件


        try{
            //创建项目目录，如果有project的name就创建到projectname下，如果没有就创建到ProjectN
            String upPath = "";
            if(projectInfo.getProName()!=null){
                upPath = projectInfo.getProMail() + "/" + projectInfo.getProName();

            }else{
                //如果没有就默认为projectN
                int proNumber = projectInfoService.getProjectNumberByProMail(projectInfo.getProMail());
                upPath = projectInfo.getProMail() + "/" + "project" + String.valueOf(proNumber);
            }

            String fileName = file.getOriginalFilename();
            //设置path目录
            projectInfo.setProRequireDocPath(upPath);
            if(file != null){
                //将文件放入对应的文件夹
                File upFileFor = new File("//bin//"+upPath);
                if(!upFileFor.exists()){
                    upFileFor.mkdirs();
                }
                File upFile = new File(upFileFor,fileName);
                file.transferTo(upFile);
                //保存文件信息
                ProjectFileInfo projectFileInfo = new ProjectFileInfo();
                projectFileInfo.setFileName(fileName);
                projectFileInfo.setFilePath(upPath);

                projectFileInfoService.saveProjectFileInfo(projectFileInfo);
            }
            //设置项目进度为0
            projectInfo.setProProcessRate("0");
            boolean sucPro = projectInfoService.saveProjectInfo(projectInfo);
            if(sucPro){
                return "true";
            }else{
                return "false";
            }




        }catch (Exception e){
            e.printStackTrace();
        }finally{

        }



        return "true";
    }

    @RequestMapping("userLogin")
    @ResponseBody
    public String userLogin() {
        return null;
    }
}
