package com.dev.admin.vo;

import com.dev.admin.entity.Menu;

import java.util.List;

/**
 * 菜单视图对象
 * Created by yuboon on 2019/02/24
 */
public class MenuVo extends Menu {

    private List<MenuVo> children;

    public List<MenuVo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuVo> children) {
        this.children = children;
    }

    public int getD_id() {
        return this.getId();
    }

    public int getD_pid() {
        return this.getParentId();
    }

}
