package com.qcz.qmplatform.system.vo;

import com.qcz.qmplatform.system.domain.User;

import java.util.List;

public class UserVo extends User {
    private List<String> organizationIds;

    private List<String> roleIds;

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public List<String> getOrganizationIds() {
        return organizationIds;
    }

    public void setOrganizationIds(List<String> organizationIds) {
        this.organizationIds = organizationIds;
    }
}
