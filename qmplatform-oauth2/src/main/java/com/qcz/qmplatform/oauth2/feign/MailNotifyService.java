package com.qcz.qmplatform.oauth2.feign;

import com.qcz.qmplatform.api.sms.dto.MailDTO;
import com.qcz.qmplatform.common.bean.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "mq", path = "/mq")
public interface MailNotifyService {

    @PostMapping("/sendMailRabbit")
    ResponseResult<?> sendMailRabbit(@RequestBody MailDTO mailDTO);
}
