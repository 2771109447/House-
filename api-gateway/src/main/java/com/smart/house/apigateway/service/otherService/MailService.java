package com.smart.house.apigateway.service.otherService;

import com.smart.house.apigateway.common.Salt;
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
     * 缓存随机码与Email的关系
     * 发送邮箱
     */
    @Async
    public void registerNotify(String email,String enableUrl) {
        String randomKey = Salt.hashString(email) + RandomStringUtils.randomAlphabetic(10);
        //缓存随机码与Email的关系
        stringRedisTemplate.opsForValue().set(randomKey, email);
        stringRedisTemplate.expire(randomKey, 1, TimeUnit.HOURS);
        String url = enableUrl +"?key="+  randomKey;
        String title="欢迎登入房产销售平台";
        sendMail(title, url, email);
    }

    /**
     * 发送邮件
     * title邮件标题
     * url:邮件内容
     * email:收件人
     */
    @Async
    public void sendMail(String title, String url, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);        //获取发件人
        message.setSubject(title);    //邮件标题
        message.setTo(email);         //邮件内容
        message.setText(url);         //目标地址
        mailSender.send(message);
    }




}
