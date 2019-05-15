package com.dev.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Role;
import com.dev.admin.entity.RoleMenu;
import com.dev.admin.mapper.RoleMapper;
import com.dev.admin.service.RoleService;
import com.dev.admin.vo.PageVo;
import com.dev.admin.vo.RoleVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 角色管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int addRole(Role role) {
        role.setCreateDate(DateUtil.date());
        return roleMapper.insert(role);
    }

    @Override
    public int addRoleMenu(int roleId, int[] menuIds) {
        roleMapper.deleteRoleMenuByRoleId(roleId);
        for (int menuId : menuIds){
            RoleMenu rm = new RoleMenu();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            roleMapper.insertRoleMenu(rm);
        }
        return menuIds.length;
    }

    @Override
    public int updateRole(Role role) {
        return roleMapper.update(role);
    }

    @Override
    public int removeRole(int id) {
        return roleMapper.delete(id);
    }

    @Override
    public int removeRoles(int[] ids) {
        return roleMapper.deleteBatch(ids);
    }

    @Override
    public PageVo getRoleList(RoleVo vo, PageVo pageVo) {
        // 分页
        Page page = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<Role> roleList = roleMapper.getRoleList(vo);
        List<RoleVo> roleVoList = CollectionUtil.newArrayList();
        for(Role r : roleList){
            RoleVo role = new RoleVo();
            BeanUtil.copyProperties(r,role);
            roleVoList.add(role);
        }
        pageVo.setList(roleVoList);
        pageVo.setTotal(page.getTotal());
        return pageVo;
    }

    @Override
    public List<RoleMenu> getMenuByRoleId(int userId) {
        List<RoleMenu> roleMenus = roleMapper.getMenuByRoleId(userId);
        return roleMenus;
    }
}
