package com.qcz.qmplatform.api.mq;

public class RabbitConstant {

    /**
     * 通知服务交换机
     */
    public static final String TOPIC_NOTIFY_EXCHANGE = "topicNotifyExchange";

    /**
     * 短信队列
     */
    public static final String QUEUE_SMS = "queue.sms";

    /**
     * 邮件队列
     */
        public static final String QUEUE_MAIL = "queue.mail";

    /**
     * 短信队列路由键
     */
    public static final String ROUTE_KEY_SMS = "routeKey.sms";

    /**
     * 邮件队列路由键
     */
    public static final String ROUTE_KEY_MAIL = "routeKey.mail";
}
