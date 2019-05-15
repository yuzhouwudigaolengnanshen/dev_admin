package com.dev.admin.service;

import com.dev.admin.entity.Task;
import com.dev.admin.vo.TaskVo;
import com.dev.admin.vo.UserVo;

import java.util.Date;
import java.util.List;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 10:26
 * @name ：TaskService
 */
public interface TaskService {

     List<TaskVo> getTaskByProjectId(int projectId);

     List<UserVo> getUserByProjectId(int projectId);

     int insertTask(Task task);

     int finishTask(int taskId, Date devFinishDatetime);

     int selectTask(int taskId);
}
