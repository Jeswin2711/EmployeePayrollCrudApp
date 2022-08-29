package com.bridgelabz.assignment.admin.sendmail;

import com.bridgelabz.assignment.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class MailSenderImpl implements IMailSender{

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Async
    public void sendEmail(String to, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage,"utf-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(content,true);
            mimeMessageHelper.setSubject("Login Details");
            mimeMessageHelper.setFrom("admin@company.com");
            mailSender.send(mimeMessage);
        }
        catch (Exception e)
        {
            System.out.println("Mail Sending Exception ---> "+e);
            throw new CustomException("Error While Sending Mail");
        }
    }
}
