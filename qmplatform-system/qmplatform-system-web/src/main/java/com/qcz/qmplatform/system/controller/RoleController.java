package com.qcz.qmplatform.system.controller;


import com.qcz.qmplatform.common.bean.PageRequest;
import com.qcz.qmplatform.common.bean.PageResult;
import com.qcz.qmplatform.common.bean.PageResultHelper;
import com.qcz.qmplatform.common.bean.PrivCode;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.system.domain.Role;
import com.qcz.qmplatform.system.service.IRoleService;
import com.qcz.qmplatform.system.vo.RolePermissionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    /**
     * 获取角色列表
     *
     * @param pageRequest 分页请求
     * @param role        请求参数
     * @return
     */
    @GetMapping("/getRoleList")
    public ResponseResult<PageResult> getRoleList(PageRequest pageRequest, Role role) {
        PageResultHelper.startPage(pageRequest);
        List<Role> roleList = roleService.getRoleList(role);
        return ResponseResult.ok(PageResultHelper.parseResult(roleList));
    }

    /**
     * 获取角色
     *
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/getRoleOne/{roleId}")
    public ResponseResult<Role> getRoleOne(@PathVariable String roleId) {
        return ResponseResult.ok(roleService.getById(roleId));
    }

    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return
     */
    @GetMapping("/getRolePermission/{roleId}")
    public ResponseResult<List> getRolePermission(@PathVariable String roleId) {
        return ResponseResult.ok(roleService.getRolePermission(roleId));
    }

    /**
     * 添加组角色
     *
     * @param role 角色信息
     * @return
     */
    @PostMapping("/addRoleOne")
    public ResponseResult addRoleOne(@RequestBody Role role) {
        if (roleService.addRoleOne(role)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 保存角色信息（新增或保存）
     *
     * @param role 角色表单信息
     * @return
     */
    @PostMapping("/saveRoleOne")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_ROLE_SAVE + "')")
    public ResponseResult saveRoleOne(@RequestBody Role role) {
        if (roleService.saveRoleOne(role)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 修改组角色
     *
     * @param role 角色信息
     * @return
     */
    @PutMapping("/updateRoleOne")
    public ResponseResult updateRoleOne(@RequestBody Role role) {
        if (roleService.updateRoleOne(role)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 删除组角色
     *
     * @param roleIds 角色id数组
     * @return
     */
    @DeleteMapping("/deleteRole")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_ROLE_DELETE + "')")
    public ResponseResult deleteRole(String roleIds) {
        if (roleService.deleteRole(Arrays.asList(roleIds.split(",")))) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    /**
     * 分配角色权限
     *
     * @param rolePermissionVo 角色id和权限id数组
     * @return
     */
    @PostMapping("/saveRolePermission")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_ROLE_ALLOT + "')")
    public ResponseResult saveRolePermission(@RequestBody RolePermissionVo rolePermissionVo) {
        if (roleService.saveRolePermission(rolePermissionVo.getRoleId(), rolePermissionVo.getPermissionIds())) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }
}

