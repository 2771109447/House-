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
     * 1������key-email ��ϵ
     * 2��ʹ��spring mail�����ʼ�
     * 3���첽����
     */
    @Async
    public void registerNotify(String email,String enableUrl) {
        String randomKey = Salt.hashString(email) + RandomStringUtils.randomAlphabetic(10);
        //ͨ��redis����key-email�İ󶨹�ϵ
        stringRedisTemplate.opsForValue().set(randomKey, email);
        stringRedisTemplate.expire(randomKey, 1, TimeUnit.HOURS);
        String url = enableUrl +"?key="+  randomKey;
        String title="��ӭע�᷿������ƽ̨!";
        sendMail(title, url, email);
    }

    /**
     * �ʼ�����
     * title���ʼ�����
     * url:�ʼ����ݣ����ӣ�
     * email:�����ַ
     */
    @Async
    public void sendMail(String title, String url, String email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);        //�ʼ�������
        message.setSubject(title);    //�ʼ�����
        message.setTo(email);         //�ռ���
        message.setText(url);         //��������
        mailSender.send(message);
    }




}
