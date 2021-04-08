package com.qcz.qmplatform.system.vo;

import java.util.List;

public class RolePermissionVo {
    private String roleId;

    private List<String> permissionIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getPermissionIds() {
        return permissionIds;
    }

    public void setPermissionIds(List<String> permissionIds) {
        this.permissionIds = permissionIds;
    }
}
