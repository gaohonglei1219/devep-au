package com.devep.vo;


import java.awt.*;

public class ProjectInfo {
    private String proId;
    private String proMail;
    private String proDuration;
    private String proRequireDocPath;
    private String proProcessRate;
    private String selectItems;
    private String proName;

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProMail() {
        return proMail;
    }

    public void setProMail(String proMail) {
        this.proMail = proMail;
    }

    public String getProDuration() {
        return proDuration;
    }

    public void setProDuration(String proDuration) {
        this.proDuration = proDuration;
    }

    public String getProRequireDocPath() {
        return proRequireDocPath;
    }

    public void setProRequireDocPath(String proRequireDocPath) {
        this.proRequireDocPath = proRequireDocPath;
    }

    public String getProProcessRate() {
        return proProcessRate;
    }

    public void setProProcessRate(String proProcessRate) {
        this.proProcessRate = proProcessRate;
    }

    public String getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(String selectItems) {
        this.selectItems = selectItems;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
                "proId='" + proId + '\'' +
                ", proMail='" + proMail + '\'' +
                ", proDuration='" + proDuration + '\'' +
                ", proRequireDocPath='" + proRequireDocPath + '\'' +
                ", proProcessRate='" + proProcessRate + '\'' +
                ", selectItems='" + selectItems + '\'' +
                ", proName='" + proName + '\'' +
                '}';
    }
}
