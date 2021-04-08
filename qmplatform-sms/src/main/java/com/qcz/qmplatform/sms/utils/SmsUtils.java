package com.qcz.qmplatform.sms.utils;

import com.qcz.qmplatform.api.sms.SmsProvider;
import com.qcz.qmplatform.sms.service.INotifyService;
import com.qcz.qmplatform.sms.service.ali.AliyunSmsNotifyService;
import com.qcz.qmplatform.sms.service.tencent.TencentCloudSmsNotifyService;

public class SmsUtils {

    public static Class<? extends INotifyService> getNotifyServiceClass(int smsProviderCode) {
        Class<? extends INotifyService> clazz = null;
        if (smsProviderCode == SmsProvider.TENCENT.code()) {
            clazz = TencentCloudSmsNotifyService.class;
        } else if (smsProviderCode == SmsProvider.ALI.code()) {
            clazz = AliyunSmsNotifyService.class;
        }

        return clazz;
    }
}
