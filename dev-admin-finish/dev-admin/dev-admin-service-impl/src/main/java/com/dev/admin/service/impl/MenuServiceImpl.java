package com.dev.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Menu;
import com.dev.admin.mapper.MenuMapper;
import com.dev.admin.service.MenuService;
import com.dev.admin.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 机构管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    @Transactional
    public int addMenu(Menu menu) {
        menu.setCreateDate(DateUtil.date());
        return menuMapper.insert(menu);
    }

    @Override
    @Transactional
    public int updateMenu(Menu menu) {
        return menuMapper.update(menu);
    }

    @Override
    @Transactional
    public int removeMenu(int id) {
        return menuMapper.delete(id);
    }

    @Override
    @Transactional
    public int removeMenus(int[] ids) {
        return menuMapper.deleteBatch(ids);
    }

    @Override
    public List<MenuVo> getMenuList(MenuVo vo) {
        List<Menu> menuList = menuMapper.getMenuList(vo);
        List<MenuVo> menuVoList = CollectionUtil.newArrayList();
        for(Menu m : menuList){
            MenuVo menu = new MenuVo();
            BeanUtil.copyProperties(m,menu);
            menuVoList.add(menu);
        }

        return menuVoList;
    }

    @Override
    public List<MenuVo> getMenuListTree(MenuVo vo) {
        List<Menu> menuList = menuMapper.getMenuListAll(vo);
        // root 0
        List<MenuVo> menuVoList = CollectionUtil.newArrayList();
        for (Menu menu: menuList) {
            if(-1 == menu.getParentId()){
                MenuVo menuVo = new MenuVo();
                BeanUtil.copyProperties(menu,menuVo);
                menuVoList.add(menuVo);
                recursive(menuVo, menuList);
            }
        }
        return menuVoList;
    }

    private void recursive(MenuVo parent, List<Menu> menuList){
        for (Menu menu: menuList) {
            if(parent.getId() == menu.getParentId()){
                MenuVo menuVo = new MenuVo();
                BeanUtil.copyProperties(menu,menuVo);

                List<MenuVo> children = parent.getChildren();
                if(children == null){
                    children = CollectionUtil.newArrayList();
                    parent.setChildren(children);
                }
                children.add(menuVo);
                recursive(menuVo, menuList);
            }
        }
    }

}
