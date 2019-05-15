package com.dev.admin.vo;

import com.dev.admin.entity.Task;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 10:26
 * @name ：TaskVo
 */
public class TaskVo extends Task {
    //项目名称
    private String projectName;
    //创建人员
    private String createUserName;
    //分配人员
    private String taskUserName;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName;
    }

    public String getTaskUserName() {
        return taskUserName;
    }

    public void setTaskUserName(String taskUserName) {
        this.taskUserName = taskUserName;
    }
}
