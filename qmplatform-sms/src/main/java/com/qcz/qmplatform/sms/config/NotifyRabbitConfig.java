package com.qcz.qmplatform.sms.config;

import com.qcz.qmplatform.api.mq.RabbitConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通知服务消息队列
 */
@Configuration
public class NotifyRabbitConfig {

    @Bean
    public TopicExchange topicNotifyExchange() {
        return new TopicExchange(RabbitConstant.TOPIC_NOTIFY_EXCHANGE);
    }

    /**
     * 短信发送队列
     */
    @Bean
    public Queue smsQueue() {
        return new Queue(RabbitConstant.QUEUE_SMS);
    }

    /**
     * 邮件发送队列
     */
    @Bean
    public Queue mailQueue() {
        return new Queue(RabbitConstant.QUEUE_MAIL);
    }

    @Bean
    public Binding bindingSmsQueue() {
        return BindingBuilder.bind(smsQueue()).to(topicNotifyExchange()).with(RabbitConstant.ROUTE_KEY_SMS);
    }

    @Bean
    public Binding bindingMailQueue() {
        return BindingBuilder.bind(mailQueue()).to(topicNotifyExchange()).with(RabbitConstant.ROUTE_KEY_MAIL);
    }
}
