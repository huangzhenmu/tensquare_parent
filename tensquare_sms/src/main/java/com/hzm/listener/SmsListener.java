package com.hzm.listener;

import com.aliyuncs.exceptions.ClientException;
import com.hzm.util.SmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 短信验证码监听类
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SmsUtil smsUtil;

    @Value("${aliyun.sms.template_code}")
    private String templateCode;

    @Value("${aliyun.sms.sign_name}")
    private String signName;

    /**
     * 监听发送过来的短信
     * @param message
     */
    @RabbitHandler
    public void sendSms(Map<String,String> message){
        System.out.println("手机号："+message.get("mobile"));
        System.out.println("验证码："+message.get("code"));
        try {
            smsUtil.sendSms(message.get("mobile"),templateCode,signName,"{\"number\":\""+message.get("code")+"\"}");
        }catch (ClientException e){
            logger.info("阿里云短信服务器异常");
        }
    }
}
