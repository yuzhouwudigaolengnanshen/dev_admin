package com.dev.admin.service;


import com.dev.admin.entity.Menu;
import com.dev.admin.vo.MenuVo;

import java.util.List;

/**
 * 菜单管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
public interface MenuService {

    int addMenu(Menu menu);

    int updateMenu(Menu menu);

    int removeMenu(int id);

    int removeMenus(int[] ids);

    List<MenuVo> getMenuList(MenuVo vo);

    List<MenuVo> getMenuListTree(MenuVo vo);

}
