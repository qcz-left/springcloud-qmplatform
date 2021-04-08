package com.qcz.qmplatform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcz.qmplatform.system.domain.UserRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<String> getRoleIdsByUserId(String userId);
}
