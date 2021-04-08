package com.qcz.qmplatform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcz.qmplatform.system.domain.Role;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface IRoleService extends IService<Role> {

    /**
     * 获取角色列表
     *
     * @param role 查询参数
     * @return
     */
    List<Role> getRoleList(Role role);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    boolean addRoleOne(Role role);

    /**
     * 修改角色
     *
     * @param role
     * @return
     */
    boolean updateRoleOne(Role role);

    /**
     * 删除角色
     *
     * @param roleIds
     * @return
     */
    boolean deleteRole(List<String> roleIds);

    boolean saveRoleOne(Role role);

    boolean saveRolePermission(String roleId, List<String> permissionIds);

    List<String> getRolePermission(String roleId);
}
