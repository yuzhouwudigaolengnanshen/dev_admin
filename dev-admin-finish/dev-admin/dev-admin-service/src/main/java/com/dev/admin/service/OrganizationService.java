package com.dev.admin.service;


import com.dev.admin.entity.Organization;
import com.dev.admin.vo.OrganizationVo;
import com.dev.admin.vo.PageVo;

import java.util.List;

/**
 * 机构管理
 * Created by 风象南(yuboon) on 2019/02/24
 */
public interface OrganizationService {

    int addOrg(Organization organization);

    int updateOrg(Organization organization);

    int removeOrg(int id);

    int removeOrgs(int[] ids);

    PageVo getOrgList(OrganizationVo vo, PageVo pageVo);

    List<OrganizationVo> getOrgListTree(OrganizationVo vo);

}
