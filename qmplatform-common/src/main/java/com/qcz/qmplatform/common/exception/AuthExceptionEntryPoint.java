package com.qcz.qmplatform.common.exception;

import cn.hutool.json.JSONUtil;
import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.common.constant.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthExceptionEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Throwable cause = authException.getCause();

        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            String errorMsg;
            if (cause instanceof InvalidTokenException) {
                errorMsg = "token验证失败，可能已过期，请重新获取！";
            } else {
                errorMsg = "token缺失！";
            }
            logger.error("msg: {}; code: {}", errorMsg, ResponseCode.UNAUTHORIZED.code());
            response.getWriter().write(JSONUtil.toJsonStr(new ResponseResult(ResponseCode.UNAUTHORIZED, errorMsg, null)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
