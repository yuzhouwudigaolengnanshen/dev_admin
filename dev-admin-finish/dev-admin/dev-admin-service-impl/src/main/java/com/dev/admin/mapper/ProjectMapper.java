package com.dev.admin.mapper;


import com.dev.admin.entity.Project;
import com.dev.admin.entity.ProjectJoin;
import com.dev.admin.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectMapper {

    int insert(Project project);

    int update(Project project);

    int delete(int projectId);

    int deleteProjectJoin(int projectId);

    List<Project> getProjectList(int createUserId);

    int addUserTask(ProjectJoin projectJoin);

    List<Project> getOwnProjectList(int userId);

    List<UserVo> getUserByProjectId(int projectId);

    int getCreateUserByProjectId(int projectId);

    Project getProjectByProjectId(int projectId);
}
