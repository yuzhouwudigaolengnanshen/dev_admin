package com.dev.admin.service;

import com.dev.admin.entity.Project;
import com.dev.admin.entity.ProjectJoin;
import com.dev.admin.vo.PageVo;

import java.util.List;

public interface ProjectService {


    int addProject(Project project);

    List<Project> getProjectList();

    int updateProject(Project project);

    int deleteProject(int projectId);

    int deleteProjectJoin(int projectId );

    int addUserTask(ProjectJoin projectJoin);

    List<Project> getOwnProjectList(int userId);

    PageVo getUserByProjectId(int projectId,PageVo pageVo);

    int getCreateUserByProjectId(int projectId);
}
