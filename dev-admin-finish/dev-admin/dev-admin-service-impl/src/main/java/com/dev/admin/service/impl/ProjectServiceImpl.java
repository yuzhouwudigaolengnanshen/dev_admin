package com.dev.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Project;
import com.dev.admin.entity.ProjectJoin;
import com.dev.admin.entity.User;
import com.dev.admin.mapper.ProjectMapper;
import com.dev.admin.service.ProjectService;
import com.dev.admin.vo.PageVo;
import com.dev.admin.vo.UserVo;
import com.dev.common.util.Constant;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private RedisTemplate redisTemplate;



    @Override
    public int addProject(Project project) {
        project.setCreateDate(DateUtil.date());
        User user =  (User)redisTemplate.opsForValue().get(Constant.cacheName);
        project.setCreateUserId(user.getId());
        return projectMapper.insert(project);
    }

    @Override
    public List<Project> getProjectList() {
        User user =  (User)redisTemplate.opsForValue().get(Constant.cacheName);
        List<Project> projectList = projectMapper.getProjectList(user.getId());
        return projectList;
    }

    @Override
    public int updateProject(Project project) {
        project.setCreateDate(DateUtil.date());
        User user =  (User)redisTemplate.opsForValue().get(Constant.cacheName);
        project.setCreateUserId(user.getId());
        return projectMapper.update(project);
    }

    @Override
    public int deleteProject(int projectId) {
        projectMapper.delete(projectId);
        return 0;
    }



    @Override
    public int deleteProjectJoin(int projectId) {
        projectMapper.deleteProjectJoin(projectId);
        return 0;
    }

    @Override
    public int addUserTask(ProjectJoin projectJoin) {
        projectMapper.addUserTask(projectJoin);
        return 0;
    }

    @Override
    public List<Project> getOwnProjectList(int userId) {
        List<Project> ownProjectList = projectMapper.getOwnProjectList(userId);
        return ownProjectList;
    }

    @Override
    public PageVo getUserByProjectId(int projectId, PageVo pageVo) {

        // 分页
        Page page = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<UserVo> userByProject = projectMapper.getUserByProjectId(projectId);        List<UserVo> userVoList = CollectionUtil.newArrayList();
        for(UserVo u : userByProject){
            UserVo vo = new UserVo();
            BeanUtil.copyProperties(u,vo);
            vo.setPassword("");
            userVoList.add(vo);
        }

        pageVo.setList(userVoList);
        pageVo.setTotal(page.getTotal());

        return pageVo;
    }

    @Override
    public int getCreateUserByProjectId(int projectId) {
        int id = projectMapper.getCreateUserByProjectId(projectId);
        return id;
    }


}
