package com.qcz.qmplatform.sms.receiver;

import com.qcz.qmplatform.api.mq.RabbitConstant;
import com.qcz.qmplatform.api.sms.dto.MailDTO;
import com.qcz.qmplatform.sms.service.mail.MailNotifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = RabbitConstant.QUEUE_MAIL)
public class MailReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailReceiver.class);

    @Autowired
    private MailNotifyService mailNotifyService;

    @RabbitHandler
    public void mailRabbitHandler(MailDTO mailDTO) {
        LOGGER.info("Receive mail queue information: {}", mailDTO);
        try {
            mailNotifyService.send(mailDTO);
        } catch (Exception e) {
            LOGGER.error("Failed to send email！", e);
        }
    }
}
