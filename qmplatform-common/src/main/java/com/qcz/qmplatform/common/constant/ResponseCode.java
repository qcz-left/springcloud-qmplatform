package com.qcz.qmplatform.common.constant;

public enum ResponseCode {

    /**
     * 成功
     */
    SUCCESS(200),

    /**
     * token即将过期（这时候通常需要续期token）
     */
    TOKEN_WILL_EXPIRE(201),
    /**
     * 失败
     */
    ERROR(400),
    /**
     * 未认证（签名错误）
     */
    UNAUTHORIZED(401),
    /**
     * 没有权限
     */
    PERMISSION_DENIED(403),
    /**
     * 接口不存在
     */
    NOT_FOUND(404),
    /**
     * 服务器内部错误
     */
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    ResponseCode(int code) {
        this.code = code;
    }

    public int code() {
        return code;
    }
}
