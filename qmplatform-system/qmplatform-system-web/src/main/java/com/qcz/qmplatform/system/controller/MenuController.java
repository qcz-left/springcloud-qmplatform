package com.qcz.qmplatform.system.controller;


import com.qcz.qmplatform.common.bean.PrivCode;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.system.domain.Menu;
import com.qcz.qmplatform.system.po.MenuTree;
import com.qcz.qmplatform.system.po.Permission;
import com.qcz.qmplatform.system.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单列表
     *
     * @param permission 请求参数
     * @return
     */
    @GetMapping("/getMenuList")
    public ResponseResult<List<MenuTree>> getMenuList(Permission permission) {
        List<MenuTree> menuList = menuService.getMenuList(permission);
        return ResponseResult.ok(menuList);
    }

    /**
     * 获取菜单/按钮信息
     *
     * @param permissionId 权限id
     * @return
     */
    @GetMapping("/getPermissionOne/{permissionId}")
    public ResponseResult<Permission> getPermissionOne(@PathVariable String permissionId) {
        return ResponseResult.ok(menuService.getPermissionById(permissionId));
    }

    /**
     * 添加菜单信息
     *
     * @param menu 菜单表单信息
     * @return
     */
    @PostMapping("/addMenuOne")
    public ResponseResult addMenuOne(@RequestBody Menu menu) {
        if (menuService.addMenuOne(menu)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改菜单信息
     *
     * @param menu 菜单表单信息
     * @return
     */
    @PutMapping("/updateMenuOne")
    public ResponseResult updateMenuOne(@RequestBody Menu menu) {
        if (menuService.updateMenuOne(menu)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 保存菜单/按钮信息（新增或保存）
     *
     * @param permission 菜单/按钮表单信息
     * @return
     */
    @PostMapping("/savePermissionOne")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_MENU_SAVE + "')")
    public ResponseResult savePermissionOne(@RequestBody Permission permission) {
        if (menuService.savePermissionOne(permission)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除菜单信息
     *
     * @param permissionIds 菜单id
     * @return
     */
    @DeleteMapping("/deleteMenu")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_MENU_DELETE + "')")
    public ResponseResult deleteMenu(String permissionIds) {
        if (menuService.deleteMenu(Arrays.asList(permissionIds.split(",")))) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}

