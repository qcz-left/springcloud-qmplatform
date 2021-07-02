package com.qcz.qmplatform.sms.receiver;

import cn.hutool.core.bean.BeanUtil;
import com.qcz.qmplatform.api.mq.RabbitConstant;
import com.qcz.qmplatform.api.sms.dto.SmsDTO;
import com.qcz.qmplatform.common.utils.SecureUtils;
import com.qcz.qmplatform.sms.NotifyServiceFactory;
import com.qcz.qmplatform.sms.bean.SmsConfig;
import com.qcz.qmplatform.sms.utils.SmsUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConstant.QUEUE_SMS)
public class SmsReceiver {

    private static final Logger logger = LoggerFactory.getLogger(SmsReceiver.class);

    @RabbitHandler
    public void smsRabbitHandler(SmsDTO smsDTO) {
        logger.info("Receive sms queue information: {}", smsDTO);
        try {
            SmsConfig smsConfig = new SmsConfig();
            BeanUtil.copyProperties(smsDTO, smsConfig);
            smsConfig.setSecretKey(SecureUtils.aesDecrypt(smsConfig.getSecretKey()));

            NotifyServiceFactory.build(SmsUtils.getNotifyServiceClass(smsDTO.getSmsProviderCode()), smsConfig).send();
        } catch (Exception e) {
            logger.error("Failed to send sms！", e);
        }
    }
}
