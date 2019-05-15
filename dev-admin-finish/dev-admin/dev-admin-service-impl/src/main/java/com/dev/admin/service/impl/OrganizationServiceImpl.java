package com.dev.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;

import com.alibaba.dubbo.config.annotation.Service;
import com.dev.admin.entity.Organization;
import com.dev.admin.mapper.OrganizationMapper;
import com.dev.admin.service.OrganizationService;
import com.dev.admin.vo.OrganizationVo;
import com.dev.admin.vo.PageVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 机构管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public int addOrg(Organization organization) {
        organization.setCreateDate(DateUtil.date());
        return organizationMapper.insert(organization);
    }

    @Override
    public int updateOrg(Organization organization) {
        return organizationMapper.update(organization);
    }

    @Override
    public int removeOrg(int id) {
        return organizationMapper.delete(id);
    }

    @Override
    public int removeOrgs(int[] ids) {
        return organizationMapper.deleteBatch(ids);
    }

    @Override
    public PageVo getOrgList(OrganizationVo vo, PageVo pageVo) {
        System.err.println("getOrgList:" + Thread.currentThread().getName());

        // 分页
        Page page = PageHelper.startPage(pageVo.getPage(), pageVo.getLimit());
        List<Organization> orgList = organizationMapper.getOrgList(vo);
        List<OrganizationVo> organizationVoList = CollectionUtil.newArrayList();
        for(Organization u : orgList){
            OrganizationVo org = new OrganizationVo();
            BeanUtil.copyProperties(u,org);
            organizationVoList.add(org);
        }

        pageVo.setList(organizationVoList);
        pageVo.setTotal(page.getTotal());
        return pageVo;
    }

    @Override
    public List<OrganizationVo> getOrgListTree(OrganizationVo vo) {
        System.err.println("getOrgListTree:" + Thread.currentThread().getName());
        List<Organization> orgList = organizationMapper.getOrgListAll(vo);
        // root 0
        List<OrganizationVo> organizationVoList = CollectionUtil.newArrayList();

        for (Organization org: orgList) {
            if(0 == org.getParentId()){
                OrganizationVo orgVo = new OrganizationVo();
                BeanUtil.copyProperties(org,orgVo);
                organizationVoList.add(orgVo);
                recursive(orgVo, orgList);
            }
        }
        return organizationVoList;
    }


    private void recursive(OrganizationVo parent, List<Organization> orgList){
        for (Organization org: orgList) {
            if(parent.getId() == org.getParentId()){
                OrganizationVo orgVo = new OrganizationVo();
                BeanUtil.copyProperties(org,orgVo);

                List<OrganizationVo> children = parent.getChildren();
                if(children == null){
                    children = CollectionUtil.newArrayList();
                    parent.setChildren(children);
                }
                children.add(orgVo);
                recursive(orgVo, orgList);
            }
        }
    }

}
