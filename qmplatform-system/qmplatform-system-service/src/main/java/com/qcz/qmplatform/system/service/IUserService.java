package com.qcz.qmplatform.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.qcz.qmplatform.system.domain.User;
import com.qcz.qmplatform.system.vo.UserVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
public interface IUserService extends IService<User> {

    UserVo queryUserByUsername(String username);

    Integer saveUser(UserVo user);

    boolean validateLoginName(String loginname, String userId);

    UserVo getUserOne(String userId);

    boolean saveUserOne(UserVo user);
}
