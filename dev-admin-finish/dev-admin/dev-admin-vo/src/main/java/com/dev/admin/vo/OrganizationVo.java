package com.dev.admin.vo;

import com.dev.admin.entity.Organization;

import java.util.List;

/**
 * 机构视图对象
 * Created by yuboon on 2019/02/24
 */
public class OrganizationVo extends Organization {

    private List<OrganizationVo> children;

    public List<OrganizationVo> getChildren() {
        return children;
    }

    public void setChildren(List<OrganizationVo> children) {
        this.children = children;
    }
}
