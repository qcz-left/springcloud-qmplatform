package com.qcz.qmplatform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcz.qmplatform.system.domain.Menu;
import com.qcz.qmplatform.system.po.MenuTree;
import com.qcz.qmplatform.system.po.Permission;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuTree> selectMenuTree(Permission permission);

    Permission getPermissionById(String id);

    void deleteMenuById(List<String> menuIds);

}
