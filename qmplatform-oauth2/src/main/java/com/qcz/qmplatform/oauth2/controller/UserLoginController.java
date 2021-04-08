package com.qcz.qmplatform.oauth2.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import com.qcz.qmplatform.api.sms.dto.MailDTO;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.common.utils.StringUtils;
import com.qcz.qmplatform.common.utils.ValidateCodeCacheUtils;
import com.qcz.qmplatform.oauth2.domain.AuthGrantType;
import com.qcz.qmplatform.oauth2.domain.AuthToken;
import com.qcz.qmplatform.oauth2.feign.MailNotifyService;
import com.qcz.qmplatform.oauth2.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserLoginController {

    @Autowired
    private IAuthService authService;

    @Autowired
    private MailNotifyService mailNotifyService;

    @Value("${auth.clientId}")
    private String clientId;

    @Value("${auth.clientSecret}")
    private String clientSecret;

    @GetMapping("/login")
    public ResponseResult<?> login(String username, String password, String validateCode) {
        if (!StringUtils.equals(ValidateCodeCacheUtils.get(username), validateCode)) {
            return ResponseResult.error("请填写正确的验证码！", null);
        }
        AuthToken authToken = authService.login(username, password, AuthGrantType.PASSWORD, clientId, clientSecret);
        if (authToken != null) {
            return ResponseResult.ok("登录成功", authToken);
        }
        return ResponseResult.error("登录失败，请检查用户名/密码是否正确！", null);
    }

    @PostMapping("/login")
    public ResponseResult<?> loginByPost(@RequestBody Map<String, String> loginForm) {
        return login(loginForm.get("username"), loginForm.get("password"), loginForm.get("validateCode"));
    }

    /**
     * 刷新token，续期
     *
     * @param refreshToken the value of refreshToken
     * @return the token
     */
    @RequestMapping("/refreshToken")
    public ResponseResult<?> refreshToken(String refreshToken) {
        AuthToken authToken = authService.refreshToken(refreshToken, AuthGrantType.REFRESH_TOKEN, clientId, clientSecret);
        if (authToken != null) {
            return ResponseResult.ok("刷新token成功", authToken);
        }
        return ResponseResult.error("刷新token失败，refreshToken验证失败或已失效！", null);
    }

    /**
     * 获取验证码
     *
     * @param loginName 登录名
     * @return the code
     */
    @GetMapping("/getValidateCode")
    public ResponseResult<?> getValidateCode(String loginName) {
        Assert.notBlank(loginName);

        String emailAddr = authService.getMailByLoginName(loginName);
        if (StringUtils.isBlank(emailAddr)) {
            return ResponseResult.error("验证码发送失败，无法找到 " + loginName + " 的邮箱账号！");
        }

        String validateCode = String.valueOf(RandomUtil.randomInt(100000, 1000000));
        ValidateCodeCacheUtils.set(loginName, validateCode);

        MailDTO mailDTO = new MailDTO();
        mailDTO.setFrom("qmplatform@163.com");
        mailDTO.setTo(emailAddr);
        mailDTO.setSubject("qmplatform平台验证码");
        mailDTO.setContent("登录验证码：" + validateCode + "，5分钟内有效，请尽快登录！");
        ResponseResult<?> responseResult = mailNotifyService.sendMailRabbit(mailDTO);
        if (responseResult.isOk()) {
            return ResponseResult.ok("验证码已发送到邮箱：" + emailAddr + "，请注意查收！", null);
        }
        return ResponseResult.error("验证码发送失败");
    }
}
