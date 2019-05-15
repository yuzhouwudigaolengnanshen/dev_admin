package com.dev.admin.web.controller;

import cn.hutool.core.collection.CollectionUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.service.LogService;
import com.dev.admin.vo.LogVo;
import com.dev.admin.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 日志管理控制器
 * Created by 风象南(yuboon) on 2019/01/19
 */
@RestController
@RequestMapping("/log")
public class LogController {

    @Reference
    private LogService logService;

    @PostMapping("/removeLogs")
    public ResponseEntity removeLogs(@RequestParam(value = "ids[]") int[] ids){
        logService.removeLogs(ids);
        return ResponseEntity.ok(0);
    }

    @RequestMapping("/getLogList")
    public ResponseEntity getLogList(LogVo logVo, PageVo pageVo){
        pageVo = logService.getLogList(logVo,pageVo);
        Map<String,Object> data = CollectionUtil.newHashMap();
        data.put("code",0);
        data.put("list",pageVo.getList());
        data.put("total",pageVo.getTotal());
        return ResponseEntity.ok(data);
    }

}
