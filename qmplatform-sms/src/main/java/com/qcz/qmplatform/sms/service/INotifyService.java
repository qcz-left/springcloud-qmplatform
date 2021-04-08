package com.qcz.qmplatform.sms.service;

import com.qcz.qmplatform.sms.bean.SmsConfig;

/**
 * 通知服务
 */
public interface INotifyService {

    /**
     * 发送
     */
    void send();

    /**
     * 设置短信网关参数，大多可以从控制台界面获取
     *
     * @param smsConfig the config of sms
     */
    default void setSmsConfig(SmsConfig smsConfig) {

    }

}
