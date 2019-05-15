package com.dev.admin.service;



import com.dev.admin.entity.Role;
import com.dev.admin.entity.RoleMenu;
import com.dev.admin.vo.PageVo;
import com.dev.admin.vo.RoleVo;

import java.util.List;

/**
 * 角色管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
public interface RoleService {

    int addRole(Role role);

    int addRoleMenu(int roleId, int[] menuIds);

    int updateRole(Role role);

    int removeRole(int id);

    int removeRoles(int[] ids);

    PageVo getRoleList(RoleVo vo, PageVo pageVo);

    List<RoleMenu> getMenuByRoleId(int userId);

}
