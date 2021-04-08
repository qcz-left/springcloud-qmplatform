package com.qcz.qmplatform.sms;

import com.qcz.qmplatform.sms.bean.SmsConfig;
import com.qcz.qmplatform.sms.service.INotifyService;

public class NotifyServiceFactory {

    public static INotifyService build(Class<? extends INotifyService> clazz) {
        return build(clazz, null);
    }

    public static INotifyService build(Class<? extends INotifyService> clazz, SmsConfig smsConfig) {
        INotifyService notifyService = null;
        try {
            notifyService = clazz.newInstance();
            notifyService.setSmsConfig(smsConfig);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return notifyService;
    }

}
