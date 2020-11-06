package com.devep.entity;

import java.io.Serializable;

public class OfflineChatInfo implements Serializable {
    private String proId;
    //标号0为用户发送给管理员，标号1位管理员回复用户
    private String sendCode;
    //1表示文字；2表示图片；3表示文件
    private String code;
    private String content;
}
