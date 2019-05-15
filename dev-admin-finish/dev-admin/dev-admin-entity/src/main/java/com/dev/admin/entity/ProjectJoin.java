package com.dev.admin.entity;

import java.io.Serializable;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/12 9:33
 * @name ：ProjectJoin
 */
public class ProjectJoin implements Serializable {
    private int id;
    private int projectId;
    private int userId;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
