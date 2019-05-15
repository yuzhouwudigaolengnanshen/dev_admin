package com.dev.admin.mapper;

import com.dev.admin.entity.Task;
import com.dev.admin.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author ：zhangpengzhan
 * @date ：Created in 2019/4/21 11:10
 * @name ：TaskMapper.xml
 */
@Mapper
public interface TaskMapper {

    List<Task> getTaskByProjectId(int projectId);

    List<UserVo> getUserByProjectId(int projectId);

    int insertTask(Task task);

    int finishTask(@Param("taskId") int taskId, @Param("devFinishDatetime") Date devFinishDatetime);

    int selectTask(int taskId);
}
