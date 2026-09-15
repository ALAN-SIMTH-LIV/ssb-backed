package com.ssb.component;

import com.ssb.config.AppConfig;
import com.ssb.exception.BusinessException;
import com.ssb.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class EmailComponent {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private AppConfig appConfig;

    /**
     * 发送验证码邮件
     *
     * @param toEmail 收件人邮箱
     */
    @Async("emailTaskExecutor")
    public void sendEmail(String toEmail){
        try {
            System.out.println("email线程: " + Thread.currentThread().getName());

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(appConfig.getEmail());
            mailMessage.setTo(toEmail);
            mailMessage.setSubject("SheepScorpioBlog");

            // 4位验证码
            String code = RandomUtil.generateCode(4);

            // 设置2分钟过期
            LocalDateTime expireTime = LocalDateTime.now().plusMinutes(2);

            String expireStr = expireTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String text = String.format("""
                    您的验证码是：%s
                    该验证码将在 %s 过期，请及时使用。
                    如非本人操作，请忽略此邮件。""", code, expireStr);

            mailMessage.setText(text);
            javaMailSender.send(mailMessage);
        } catch (Exception e){
            throw new BusinessException("邮件发送失败，原因: " + e.getMessage(),e);
        }
    }

}
