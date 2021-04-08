package com.qcz.qmplatform.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.system.domain.Role;
import com.qcz.qmplatform.system.domain.RolePermission;
import com.qcz.qmplatform.system.mapper.RoleMapper;
import com.qcz.qmplatform.system.service.IRolePermissionService;
import com.qcz.qmplatform.system.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private IRolePermissionService rolePermissionService;

    @Override
    public List<Role> getRoleList(Role role) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        String roleName = role.getRoleName();
        wrapper.like(StringUtils.isNotBlank(roleName), "role_name", roleName);
        return list(wrapper);
    }

    @Override
    public boolean addRoleOne(Role role) {
        role.setRoleId(StringUtils.uuid());
        return save(role);
    }

    @Override
    public boolean updateRoleOne(Role role) {
        return updateById(role);
    }

    @Override
    public boolean deleteRole(List<String> roleIds) {
        return removeByIds(roleIds);
    }

    @Override
    public boolean saveRoleOne(Role role) {
        if (StringUtils.isBlank(role.getRoleId())) {
            return addRoleOne(role);
        }
        return updateRoleOne(role);
    }

    @Override
    public boolean saveRolePermission(String roleId, List<String> permissionIds) {
        QueryWrapper<RolePermission> wrapper = new QueryWrapper<>();
        wrapper.eq("role_id", roleId);
        rolePermissionService.remove(wrapper);

        List<RolePermission> saveRolePermissions = new ArrayList<>();
        for (String permissionId : permissionIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            saveRolePermissions.add(rolePermission);
        }
        return rolePermissionService.saveBatch(saveRolePermissions, saveRolePermissions.size());
    }

    @Override
    public List<String> getRolePermission(String roleId) {
        return baseMapper.getPermissionIdsByRoleId(roleId);
    }
}
