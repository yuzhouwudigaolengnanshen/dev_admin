package com.dev.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 10:22
 * @name ：Task
 */
public class Task implements Serializable {
    private int id;
    private int projectId;
    private String title;
    private String content;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date createDatetime;
    private int createUserId;
    private int devUserId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date devFinishDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public int getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    public int getDevUserId() {
        return devUserId;
    }

    public void setDevUserId(int devUserId) {
        this.devUserId = devUserId;
    }

    public Date getDevFinishDatetime() {
        return devFinishDatetime;
    }

    public void setDevFinishDatetime(Date devFinishDatetime) {
        this.devFinishDatetime = devFinishDatetime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDatetime=" + createDatetime +
                ", createUserId=" + createUserId +
                ", devUserId=" + devUserId +
                ", devFinishDatetime=" + devFinishDatetime +
                '}';
    }
}
