package com.devep.mapper;

import com.devep.vo.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {

    int getUserMailCount(@Param("userMail") String userMail);

    int saveUserInfo(UserInfo userInfo);

    UserInfo getUserInfoByUserMail(@Param("userMail") String userMail);
}
