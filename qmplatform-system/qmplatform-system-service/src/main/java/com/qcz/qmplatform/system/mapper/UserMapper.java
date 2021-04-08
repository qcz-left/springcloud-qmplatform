package com.qcz.qmplatform.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qcz.qmplatform.system.domain.User;
import com.qcz.qmplatform.system.vo.UserVo;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface UserMapper extends BaseMapper<User> {
    UserVo queryUserByUsername(String username);
}
