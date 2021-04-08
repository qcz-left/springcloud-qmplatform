package com.qcz.qmplatform.system.po;

import com.qcz.qmplatform.common.bean.Tree;

/**
 * 树形菜单数据，这里和按钮数据一起展示成权限数据
 */
public class MenuTree extends Tree {

    /**
     * 图标字符串，一般只有菜单才有图标
     */
    private String icon;

    /**
     * 权限码（权限标识）
     */
    private String code;

    /**
     * 链接路径
     */
    private String linkUrl;

    /**
     * 排序
     */
    private Integer iorder;

    /**
     * 权限类型（1：菜单，2：按钮）
     */
    private Integer permissionType;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getIorder() {
        return iorder;
    }

    public void setIorder(Integer iorder) {
        this.iorder = iorder;
    }

    public Integer getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(Integer permissionType) {
        this.permissionType = permissionType;
    }
}
