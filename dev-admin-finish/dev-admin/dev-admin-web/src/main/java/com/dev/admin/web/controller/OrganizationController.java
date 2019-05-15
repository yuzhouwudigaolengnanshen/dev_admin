package com.dev.admin.web.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.service.OrganizationService;
import com.dev.admin.vo.OrganizationVo;
import com.dev.admin.vo.PageVo;
import com.dev.admin.web.annotation.MenuName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 机构管理控制器
 * Created by 风象南(yuboon) on 2019/01/19
 */
@RestController
@RequestMapping("/org")
@MenuName("机构管理")
public class OrganizationController {

    @Reference
    private OrganizationService organizationService;

    @PostMapping("/addOrUpdateOrg")
    public ResponseEntity addOrUpdateOrg(OrganizationVo organizationVo){
        if(organizationVo.getId() == 0){
            organizationService.addOrg(organizationVo);
        }else{
            organizationService.updateOrg(organizationVo);
        }
        return ResponseEntity.ok(0);
    }

    @PostMapping("/removeOrg")
    public ResponseEntity removeOrg(int id){
        organizationService.removeOrg(id);
        return ResponseEntity.ok(0);
    }

    @PostMapping("/removeOrgs")
    public ResponseEntity removeOrg(@RequestParam(value = "ids[]") int[] ids){
        organizationService.removeOrgs(ids);
        return ResponseEntity.ok(0);
    }

    @RequestMapping("/getOrgList")
    public ResponseEntity getOrgList(OrganizationVo orgVo, PageVo pageVo){

        String name = orgVo.getName();
        String code = orgVo.getCode();

        if(StrUtil.isNotBlank(name)){
            orgVo.setName(name + "%");
        }
        if(StrUtil.isNotBlank(code)){
            orgVo.setCode(code + "%");
        }

        pageVo = organizationService.getOrgList(orgVo,pageVo);
        Map<String,Object> data = CollectionUtil.newHashMap();
        data.put("code",0);
        data.put("list",pageVo.getList());
        data.put("total",pageVo.getTotal());
        return ResponseEntity.ok(data);
    }

    @RequestMapping("/getOrgListTree")
    public ResponseEntity getOrgListTree(OrganizationVo orgVo){
        List<OrganizationVo> organizationVoList = organizationService.getOrgListTree(orgVo);
        return ResponseEntity.ok(organizationVoList);
    }

   /* public String createUser2(@Valid UserInfo userInfo, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return bindingResult.getFieldError().getDefaultMessage();
        }
        userService.createUser(userInfo.getTel(),userInfo.getPassWord());
        return "OK";
    }
*/

}
