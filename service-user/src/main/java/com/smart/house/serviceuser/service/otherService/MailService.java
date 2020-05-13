package com.smart.house.serviceuser.service.otherService;

import com.smart.house.serviceuser.common.Salt;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    /**
     * 1，缓存key-email 关系
     * 2，使用spring mail发送邮件
     * 3，异步发送
     */
    @Async
    public void registerNotify(String email,String enableUrl) {
        String randomKey = Salt.hashString(email) + RandomStringUtils.randomAlphabetic(10);
        //通过redis缓存key-email的绑定关系
        stringRedisTemplate.opsForValue().set(randomKey, email);
        stringRedisTemplate.expire(randomKey, 1, TimeUnit.HOURS);
        String url = enableUrl +"?key="+  randomKey;
        String title="欢迎注册房产售卖平台!";
        sendMail(title, url, email);
    }

    /**
     * 邮件发送
     * title：邮件标题
     * url:邮件内容（链接）
     * email:邮箱地址
     */
    @Async
    public void sendMail(String title, String url, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);        //邮件发件人
        message.setSubject(title);    //邮件标题
        message.setTo(email);         //收件人
        message.setText(url);         //发送内容
        mailSender.send(message);
    }




}
