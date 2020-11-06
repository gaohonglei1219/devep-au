package com.devep.controller;

import com.devep.service.UserInfoService;
import com.devep.vo.UserInfo;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/userLogin")
    @ResponseBody
    public Map<String,String> userLogin(@Param("mail") String mail, String pass, HttpSession session){
        Map map = new HashedMap();
        //检查是否存在用户
        UserInfo user = userInfoService.getUserInfoByUserMail(mail);
        if(user != null){
            //检查密码
            if(user.getUserPass().equals(pass)){
                session.setAttribute("user",user);
                String mess = "true";
                map.put("mess",mess);
            }else{
                String mess = "incorrect password";
                map.put("mess",mess);
            }
        }else{
            String mess = "unknown user";
            map.put("mess",mess);
        }



        return map;
    }
}
