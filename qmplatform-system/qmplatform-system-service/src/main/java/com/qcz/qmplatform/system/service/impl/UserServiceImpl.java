package com.qcz.qmplatform.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcz.qmplatform.common.utils.PasswordUtils;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.system.domain.User;
import com.qcz.qmplatform.system.domain.UserOrganization;
import com.qcz.qmplatform.system.domain.UserRole;
import com.qcz.qmplatform.system.mapper.UserMapper;
import com.qcz.qmplatform.system.mapper.UserOrganizationMapper;
import com.qcz.qmplatform.system.mapper.UserRoleMapper;
import com.qcz.qmplatform.system.service.IUserService;
import com.qcz.qmplatform.system.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户服务实现类
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserOrganizationMapper userOrganizationMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserVo queryUserByUsername(String username) {
        return baseMapper.queryUserByUsername(username);
    }

    @Override
    public Integer saveUser(UserVo user) {
        String userId = user.getId();
        if (userId == null) {
            return baseMapper.insert(user);
        } else {
            return baseMapper.updateById(user);
        }
    }

    /**
     * 校验登录名唯一
     *
     * @param loginname the login name
     * @param userId    the user id
     * @return true: not exists , or false: exists
     */
    @Override
    public boolean validateLoginName(String loginname, String userId) {
        Assert.notBlank(loginname);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.ne(StringUtils.isNotBlank(userId), "id", userId);
        wrapper.eq("loginname", loginname);
        return baseMapper.selectCount(wrapper) == 0;
    }

    @Override
    public UserVo getUserOne(String userId) {
        User user = getById(userId);
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        userVo.setOrganizationIds(userOrganizationMapper.getOrganizationIdsByUserId(userId));
        userVo.setRoleIds(userRoleMapper.getRoleIdsByUserId(userId));
        return userVo;
    }

    @Override
    public boolean saveUserOne(UserVo user) {
        String userId = user.getId();
        String password = user.getPassword();
        if (StringUtils.isBlank(userId)) {
            userId = StringUtils.uuid();
            user.setId(userId);
            if (StringUtils.isNotBlank(user.getPassword())) {
                user.setPassword(PasswordUtils.encode(user.getPassword()));
            }
            insertUserOrg(userId, user.getOrganizationIds());
            return save(user);
        }
        // 先删除关联的部门和角色，在增加
        Map<String, Object> params = new HashMap<>(2);
        params.put("user_id", userId);
        userOrganizationMapper.deleteByMap(params);
        userRoleMapper.deleteByMap(params);
        insertUserOrg(userId, user.getOrganizationIds());
        insertUserRole(userId, user.getRoleIds());
        // 处理密码
        String newPwd;
        if (PasswordUtils.passwordChange(password)) {
            newPwd = PasswordUtils.encode(password);
        } else {
            newPwd = getById(user.getId()).getPassword();
        }
        user.setPassword(newPwd);
        return updateById(user);
    }

    private void insertUserOrg(String userId, List<String> organizationIds) {
        for (String organizationId : organizationIds) {
            UserOrganization userOrganization = new UserOrganization();
            userOrganization.setUserId(userId);
            userOrganization.setOrganizationId(organizationId);
            userOrganizationMapper.insert(userOrganization);
        }
    }

    private void insertUserRole(String userId, List<String> roleIds) {
        for (String roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
    }
}
