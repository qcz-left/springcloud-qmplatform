package com.qcz.qmplatform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcz.qmplatform.system.domain.Menu;
import com.qcz.qmplatform.system.po.MenuTree;
import com.qcz.qmplatform.system.po.Permission;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface IMenuService extends IService<Menu> {

    /**
     * 获取菜单列表
     *
     * @param permission 查询参数
     * @return
     */
    List<MenuTree> getMenuList(Permission permission);

    /**
     * 添加菜单
     *
     * @param menu
     * @return
     */
    boolean addMenuOne(Menu menu);

    /**
     * 修改菜单
     *
     * @param menu
     * @return
     */
    boolean updateMenuOne(Menu menu);

    /**
     * 保存菜单
     *
     * @param menu
     * @return
     */
    boolean saveMenuOne(Menu menu);

    /**
     * 保存菜单
     *
     * @param permission
     * @return
     */
    boolean savePermissionOne(Permission permission);

    /**
     * 删除菜单
     *
     * @param menuIds
     * @return
     */
    boolean deleteMenu(List<String> menuIds);

    Permission getPermissionById(String permissionId);
}
