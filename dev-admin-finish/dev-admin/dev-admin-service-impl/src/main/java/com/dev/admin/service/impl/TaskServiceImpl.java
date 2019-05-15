package com.dev.admin.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Project;
import com.dev.admin.entity.Task;
import com.dev.admin.entity.User;
import com.dev.admin.mapper.ProjectMapper;
import com.dev.admin.mapper.TaskMapper;
import com.dev.admin.mapper.UserMapper;
import com.dev.admin.service.TaskService;
import com.dev.admin.vo.TaskVo;
import com.dev.admin.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 10:27
 * @name ：TaskServiceImpl
 */
@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    @Transactional
    public List<TaskVo> getTaskByProjectId(int projectId) {
        List<Task> taskList = taskMapper.getTaskByProjectId(projectId);

//        for (Task task : taskList) {
//            System.out.println(task);
//            System.out.println("**************");
//        }

        List<TaskVo> taskVos = new ArrayList<>();
        for (Task task : taskList) {
            TaskVo vo = new TaskVo();
            vo.setId(task.getId());
            vo.setTitle(task.getTitle());
            vo.setContent(task.getContent());
            vo.setCreateDatetime(task.getCreateDatetime());
            User createUser = userMapper.getUserById(task.getCreateUserId());
            vo.setCreateUserName(createUser.getName());
            User devUser = userMapper.getUserById(task.getDevUserId());
            vo.setTaskUserName(devUser.getName());
            Project project = projectMapper.getProjectByProjectId(task.getProjectId());
            vo.setProjectName(project.getName());
            taskVos.add(vo);
        }
        return taskVos;
    }

    @Override
    public List<UserVo> getUserByProjectId(int projectId) {
        List<UserVo> userByProjectId = taskMapper.getUserByProjectId(projectId);
        return userByProjectId;
    }

    @Override
    public int insertTask(Task task) {
        taskMapper.insertTask(task);
        return 0;
    }

    @Override
    public int finishTask(int taskId,Date devFinishDatetime) {
        taskMapper.finishTask(taskId,devFinishDatetime);
        return 0;
    }

    @Override
    public int selectTask(int taskId) {
        return 0;
    }
}
