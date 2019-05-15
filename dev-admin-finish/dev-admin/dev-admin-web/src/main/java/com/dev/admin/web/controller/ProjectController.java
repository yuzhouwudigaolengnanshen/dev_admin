package com.dev.admin.web.controller;

/*
    项目研发控制器


 */


import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.entity.Project;
import com.dev.admin.entity.ProjectJoin;
import com.dev.admin.service.ProjectService;
import com.dev.admin.vo.PageVo;
import com.dev.admin.vo.ProjectVo;
import com.dev.admin.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Reference
    private ProjectService projectService;



    @RequestMapping("/addOrUpdateProject")
    public ResponseEntity addOrUpdateProject(ProjectVo projectVo){
        if (projectVo.getId() == 0){
            //add project
            projectService.addProject(projectVo);
        }else {
            //update project
            projectService.updateProject(projectVo);
        }
        return ResponseEntity.ok(0);
    }


    @GetMapping("/getProjectList")
    public ResponseEntity getProjectList(){
        List<Project> projectList = projectService.getProjectList();
        return ResponseEntity.ok(projectList);
    }

    @RequestMapping("/deleteProject")
    @Transactional
    public ResponseEntity deleteProject(int projectId){
        projectService.deleteProject(projectId);
        projectService.deleteProjectJoin(projectId);
        return ResponseEntity.ok(0);
    }


    @RequestMapping("/AddUserTask")
    @Transactional
    public ResponseEntity addUserTask(int projectId,@RequestParam(value = "userIds[]") int[] ids){
        //System.out.println(projectId);
        projectService.deleteProjectJoin(projectId);
        for (int id : ids) {
            ProjectJoin projectJoin = new ProjectJoin();
            projectJoin.setProjectId(projectId);
            projectJoin.setUserId(id);
            projectService.addUserTask(projectJoin);
        }
        return ResponseEntity.ok(0);
    }

    @RequestMapping("/getOwnProjectList")
    public ResponseEntity getOwnProjectList(int userId){
        //System.out.println(userId);
        List<Project> ownProjectList = projectService.getOwnProjectList(userId);
        return ResponseEntity.ok(ownProjectList);
    }

    @RequestMapping("/getUserByProjectId")
    public ResponseEntity getUserByProjectId(int projectId, PageVo pageVo){
        pageVo = projectService.getUserByProjectId(projectId,pageVo);
        //return ResponseEntity.ok(userByProjectId);

        Map<String,Object> data = CollectionUtil.newHashMap();
        data.put("code",0);
        data.put("list",pageVo.getList());
        data.put("total",pageVo.getTotal());
        return ResponseEntity.ok(data);

    }


}
