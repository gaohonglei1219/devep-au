package com.devep.service;


import com.devep.mapper.UserInfoMapper;
import com.devep.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMappper;

    public Boolean getUserMailExists(String userMail) {
        int countMail = userInfoMappper.getUserMailCount(userMail);
        if(countMail>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean saveUserInfo(UserInfo userInfo) {
        return(userInfoMappper.saveUserInfo(userInfo)>0?true:false);
    }

    public UserInfo getUserInfoByUserMail(String userMail){
        return userInfoMappper.getUserInfoByUserMail(userMail);
    }
}
