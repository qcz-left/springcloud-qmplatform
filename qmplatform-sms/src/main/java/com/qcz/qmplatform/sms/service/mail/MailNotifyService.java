package com.qcz.qmplatform.sms.service.mail;

import com.qcz.qmplatform.api.sms.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * 邮件通知服务
 */
@Service
public class MailNotifyService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(MailDTO mailDTO) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(mailDTO.getFrom());
        mailMessage.setTo(mailDTO.getTo());
        mailMessage.setText(mailDTO.getContent());
        mailMessage.setSubject(mailDTO.getSubject());
        javaMailSender.send(mailMessage);
    }

}
