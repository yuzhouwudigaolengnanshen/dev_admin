package com.dev.admin.service;



import com.dev.admin.entity.User;
import com.dev.admin.entity.UserRole;
import com.dev.admin.vo.MenuVo;
import com.dev.admin.vo.PageVo;
import com.dev.admin.vo.UserVo;

import java.util.List;

/**
 * 用户管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
public interface UserService {

    int addUser(User user);

    int addRole(int userId, int[] roleIds);

    int updateUser(User user);

    int updatePass(User user);

    int removeUser(int id);

    int removeUsers(int[] ids);

    PageVo getUserList(UserVo userVo, PageVo pageVo);

    User getUserByLogiName(String loginName);

    List<UserRole> getRoleByUserId(int userId);

    List<MenuVo> getUserMenus();

    User getUserById(int id);

    String getOrgName(int orgId);
}
