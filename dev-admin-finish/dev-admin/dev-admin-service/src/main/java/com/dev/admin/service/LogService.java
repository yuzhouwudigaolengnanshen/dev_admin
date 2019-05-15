package com.dev.admin.service;

import com.dev.admin.entity.Log;
import com.dev.admin.vo.LogVo;
import com.dev.admin.vo.PageVo;


/**
 * 日志管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
public interface LogService {

    int addLog(Log log);

    int removeLogs(int[] ids);

    PageVo getLogList(LogVo vo, PageVo pageVo);

}
