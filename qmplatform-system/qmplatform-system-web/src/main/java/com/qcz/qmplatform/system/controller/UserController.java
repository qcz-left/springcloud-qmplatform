package com.qcz.qmplatform.system.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qcz.qmplatform.common.bean.PageRequest;
import com.qcz.qmplatform.common.bean.PageResult;
import com.qcz.qmplatform.common.bean.PageResultHelper;
import com.qcz.qmplatform.common.bean.PrivCode;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.common.utils.DateUtils;
import com.qcz.qmplatform.common.utils.SecureUtils;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.system.domain.User;
import com.qcz.qmplatform.system.service.IUserService;
import com.qcz.qmplatform.system.vo.UserVo;
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

/**
 * <p>
 * 用户前端控制器
 * </p>
 *
 * @author quchangzhong
 * @since 2020-09-03
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUser/{userId}")
    public UserVo getUserInfo(@PathVariable String userId) {
        return userService.getUserOne(userId);
    }

    @GetMapping("/getUserList")
    public ResponseResult<PageResult> getUserList(PageRequest pageRequest, String username, String userSex) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(username), "username", username);
        queryWrapper.like(StrUtil.isNotBlank(userSex), "user_sex", userSex);
        PageResultHelper.startPage(pageRequest);
        return ResponseResult.ok(PageResultHelper.parseResult(userService.list(queryWrapper)));
    }

    /**
     * 校验登录名唯一
     *
     * @param loginname 登录名
     * @return
     */
    @GetMapping("/validateLoginName")
    public ResponseResult<?> validateLoginName(String loginname, String userId) {
        if (!userService.validateLoginName(loginname, userId)) {
            return ResponseResult.error("登录名已存在！", loginname);
        }
        return ResponseResult.ok();
    }

    @GetMapping("/queryUserByUsername")
    public ResponseResult<UserVo> queryUserByUsername(String username) {
        return ResponseResult.ok(userService.queryUserByUsername(username));
    }

    @PostMapping("/addUser")
    public ResponseResult<Boolean> addUser(@RequestBody UserVo user) {
        user.setId(StringUtils.uuid());
        user.setCreateTime(DateUtils.getCurrTimestamp());
        if (StringUtils.isNotBlank(user.getPassword())) {
            user.setPassword(SecureUtils.aesEncrypt(user.getPassword()));
        }
        return ResponseResult.ok(userService.save(user));
    }

    @PutMapping("/updateUser")
    public ResponseResult<Boolean> updateUser(@RequestBody UserVo user) {
        String password = user.getPassword();
        String newPwd;
        if (SecureUtils.passwordChanged(password)) {
            newPwd = SecureUtils.aesEncrypt(password);
        } else {
            newPwd = userService.getById(user.getId()).getPassword();
        }
        user.setPassword(newPwd);
        return ResponseResult.ok(userService.updateById(user));
    }

    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    @PostMapping("/saveUser")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_USER_SAVE + "')")
    public ResponseResult<?> saveUser(@RequestBody UserVo user) {
        if (userService.saveUserOne(user)) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

    @DeleteMapping("/delUser")
    @PreAuthorize("hasAuthority('" + PrivCode.BTN_CODE_USER_DELETE + "')")
    public ResponseResult delUser(String userIds) {
        if (userService.removeByIds(Arrays.asList(userIds.split(",")))) {
            return ResponseResult.ok();
        }
        return ResponseResult.error();
    }

}

