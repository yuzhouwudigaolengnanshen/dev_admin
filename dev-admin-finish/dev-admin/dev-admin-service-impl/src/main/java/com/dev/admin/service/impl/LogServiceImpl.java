package com.dev.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Log;
import com.dev.admin.mapper.LogMapper;
import com.dev.admin.service.LogService;
import com.dev.admin.vo.LogVo;
import com.dev.admin.vo.PageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 日志管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int addLog(Log log) {
        log.setCreateDatetime(DateUtil.date());
        return logMapper.insert(log);
    }

    @Override
    @Transactional
    public int removeLogs(int[] ids) {
        return logMapper.deleteBatch(ids);
    }

    @Override
    public PageVo getLogList(LogVo vo, PageVo pageVo) {
        // 分页
        Page page = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<Log> logList = logMapper.getLogList(vo);
        List<LogVo> logVoList = CollectionUtil.newArrayList();
        for(Log log : logList){
            LogVo logVo = new LogVo();
            BeanUtil.copyProperties(log,logVo);
            logVoList.add(logVo);
        }
        pageVo.setList(logVoList);
        pageVo.setTotal(page.getTotal());
        return pageVo;
    }

}
