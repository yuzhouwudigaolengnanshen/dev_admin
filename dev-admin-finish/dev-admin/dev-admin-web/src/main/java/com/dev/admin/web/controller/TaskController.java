package com.dev.admin.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.entity.Project;
import com.dev.admin.entity.Task;
import com.dev.admin.service.ProjectService;
import com.dev.admin.service.TaskService;
import com.dev.admin.vo.TaskVo;
import com.dev.admin.vo.UserVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 10:19
 * @name ：TaskController
 */

@RestController
@RequestMapping("/task")
public class TaskController {

    @Reference
    private TaskService taskService;
    @Reference
    private ProjectService projectService;

    @GetMapping("/getTaskByProjectId")
    public ResponseEntity getTaskByProjectId(int projectId){
        List<TaskVo> list = taskService.getTaskByProjectId(projectId);

        Map<String,Object> data = CollectionUtil.newHashMap();
        data.put("code",0);
        data.put("list",list);
        data.put("total",list.size());
        return ResponseEntity.ok(data);
    }

    @GetMapping("/getUserByProjectId")
    public ResponseEntity getUserByProjectId(int projectId){
        List<UserVo> userByProjectId = taskService.getUserByProjectId(projectId);
        return ResponseEntity.ok(userByProjectId);
    }

    @PostMapping("/insertTask")
    public ResponseEntity insertTask(TaskVo taskVo){
        taskVo.setCreateDatetime(DateUtil.date());
        int id = projectService.getCreateUserByProjectId(taskVo.getProjectId());
        taskVo.setCreateUserId(id);
        //System.out.println(taskVo);
        taskService.insertTask(taskVo);
        return ResponseEntity.ok(0);
    }

    @PostMapping("/finishTask")
    public ResponseEntity finishTask(int taskId){
        DateTime date = DateUtil.date();
        taskService.finishTask(taskId,date);
        return ResponseEntity.ok(0);
    }


}
