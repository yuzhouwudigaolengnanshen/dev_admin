package com.dev.admin.web.controller;

import cn.hutool.core.collection.CollectionUtil;

import com.alibaba.dubbo.config.annotation.Reference;
import com.dev.admin.service.MenuService;
import com.dev.admin.vo.MenuVo;
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
 * 菜单管理控制器
 * Created by 风象南(yuboon) on 2019/01/19
 */
@RestController
@RequestMapping("/menu")
@MenuName("菜单管理")
public class MenuController {

    @Reference
    private MenuService menuService;

    @PostMapping("/addOrUpdateMenu")
    public ResponseEntity addOrUpdateMenu(MenuVo menuVo){
        if(menuVo.getId() == 0){
            menuService.addMenu(menuVo);
        }else{
            menuService.updateMenu(menuVo);
        }
        return ResponseEntity.ok(0);
    }

    @PostMapping("/removeMenu")
    public ResponseEntity removeMenu(int id){
        menuService.removeMenu(id);
        return ResponseEntity.ok(0);
    }

    @PostMapping("/removeMenus")
    public ResponseEntity removeMenu(@RequestParam(value = "ids[]") int[] ids){
        menuService.removeMenus(ids);
        return ResponseEntity.ok(0);
    }

    @RequestMapping("/getMenuList")
    public ResponseEntity getMenuList(MenuVo menuVo){
        List<MenuVo> menuList = menuService.getMenuList(menuVo);
        Map<String,Object> data = CollectionUtil.newHashMap();

        data.put("code",0);
        data.put("msg","ok");
        data.put("data",menuList);
        data.put("count",menuList.size());
        return ResponseEntity.ok(data);
    }

    @RequestMapping("/getMenuListTree")
    public ResponseEntity getMenuListTree(MenuVo vo){
        List<MenuVo> menuVoList = menuService.getMenuListTree(vo);
        Map<String,Object> data = CollectionUtil.newHashMap();
        data.put("code",0);
        data.put("list",menuVoList);
        data.put("total",menuVoList.size());
        return ResponseEntity.ok(data);
    }
}
