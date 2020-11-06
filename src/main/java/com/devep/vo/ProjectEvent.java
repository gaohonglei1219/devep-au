package com.devep.vo;

public class ProjectEvent {
    private String eventId;
    private String proId;
    private String eventTime;
    private String eventContent;

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventContent() {
        return eventContent;
    }

    public void setEventContent(String eventContent) {
        this.eventContent = eventContent;
    }

    @Override
    public String toString() {
        return "projectEvent{" +
                "eventId='" + eventId + '\'' +
                ", proId='" + proId + '\'' +
                ", eventTime='" + eventTime + '\'' +
                ", eventContent='" + eventContent + '\'' +
                '}';
    }
}
