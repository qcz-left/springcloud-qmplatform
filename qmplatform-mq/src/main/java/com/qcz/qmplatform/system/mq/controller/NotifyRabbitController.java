package com.qcz.qmplatform.system.mq.controller;

import com.qcz.qmplatform.api.mq.RabbitConstant;
import com.qcz.qmplatform.api.sms.dto.MailDTO;
import com.qcz.qmplatform.api.sms.dto.SmsDTO;
import com.qcz.qmplatform.common.bean.ResponseResult;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotifyRabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/sendSmsRabbit")
    public ResponseResult<?> sendSmsRabbit(@RequestBody SmsDTO smsDTO) {
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_NOTIFY_EXCHANGE, RabbitConstant.ROUTE_KEY_SMS, smsDTO);
        return ResponseResult.ok();
    }

    @PostMapping("/sendMailRabbit")
    public ResponseResult<?> sendMailRabbit(@RequestBody MailDTO mailDTO) {
        rabbitTemplate.convertAndSend(RabbitConstant.TOPIC_NOTIFY_EXCHANGE, RabbitConstant.ROUTE_KEY_MAIL, mailDTO);
        return ResponseResult.ok();
    }

}
