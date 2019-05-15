package com.dev.admin.web.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.service.DomeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dome")
public class DomeController {


    @Reference
    private DomeService domeService;

    @RequestMapping("/get")
    public String getUserList() throws JsonProcessingException {
        List userList = domeService.getUserList();
        ObjectMapper mapper = new ObjectMapper();
        String string = mapper.writeValueAsString(userList);
        return string;
    }
    @GetMapping("/save")
    public String saveUser(String username){
        System.out.println(username);
        domeService.saveUser(username);
        return "ok";
    }
}
