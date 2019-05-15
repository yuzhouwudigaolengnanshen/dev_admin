package com.dev.admin.vo;

import cn.hutool.core.util.StrUtil;
import com.dev.admin.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户视图对象
 * Created by yuboon on 2019/02/24
 */
public class UserVo extends User {

    @JsonProperty("LAY_CHECKED")
    private boolean LAY_CHECKED;

    private String projectId;

    private String orgName;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRegionText() {

        /*<option value="0">北京</option>
        <option value="1">上海</option>
        <option value="2">广州</option>
        <option value="3">深圳</option>
        <option value="4">西安</option>*/



        if(this.getRegion() == 0){
            return "北京";
        }

        if(this.getRegion() == 1){
            return "上海";
        }

        if(this.getRegion() == 2){
            return "广州";
        }

        if(this.getRegion() == 3){
            return "深圳";
        }

        if(this.getRegion() == 4){
            return "西安";
        }

        return "未知";
    }

    public boolean isLAY_CHECKED() {
        return StrUtil.isNotBlank(projectId);
    }

    public void setLAY_CHECKED(boolean LAY_CHECKED) {
        this.LAY_CHECKED = LAY_CHECKED;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
