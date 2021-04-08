package com.qcz.qmplatform.common.exception;

import com.qcz.qmplatform.common.bean.ResponseResult;
import com.qcz.qmplatform.common.constant.ResponseCode;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler implements ErrorController {

    /**
     * 服务器异常500
     */
    @ExceptionHandler(Exception.class)
    public ResponseResult errorHandleBy500(Exception ex) {
        ex.printStackTrace();
        return new ResponseResult(ResponseCode.INTERNAL_SERVER_ERROR, "服务器好像出现错误了，请联系管理员！", null);
    }

    /**
     * 没有权限403
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseResult errorHandleBy403(AccessDeniedException ex) {
        ex.printStackTrace();
        return new ResponseResult(ResponseCode.PERMISSION_DENIED, "没有该资源权限！", null);
    }

    /**
     * 未认证401
     */
    @ExceptionHandler(UnauthorizedUserException.class)
    public ResponseResult errorHandleBy401(UnauthorizedUserException ex) {
        ex.printStackTrace();
        return new ResponseResult(ResponseCode.UNAUTHORIZED, "未认证！", null);
    }

    /**
     * 页面找不到404
     */
    @RequestMapping("/common-error")
    public ResponseResult errorHandleBy404(NoHandlerFoundException ex) {
        ex.printStackTrace();
        return new ResponseResult(ResponseCode.NOT_FOUND, "找不到接口！", null);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(CommonException.class)
    public ResponseResult errorHandleByCommon(CommonException ex) {
        ex.printStackTrace();
        return ResponseResult.error(ex.getMessage(), null);
    }

    @Override
    public String getErrorPath() {
        return "/common-error";
    }

}
