package com.bridgelabz.assignment.sendmail;

import com.bridgelabz.assignment.employee.repository.EmployeePayrollRepository;
import com.bridgelabz.assignment.exception.CustomException;
import com.bridgelabz.assignment.utility.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.mail.internet.MimeMessage;

@Service
public class MailSenderImpl implements IMailSender{

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmployeePayrollRepository repository;

    @Override
    @Async
    public void sendEmail(String to, String content) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper =
                    new MimeMessageHelper(mimeMessage,"utf-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(content,true);
            mimeMessageHelper.setSubject("Password Reset");
            mimeMessageHelper.setFrom("admin@company.com");
            mailSender.send(mimeMessage);
        }
        catch (Exception e)
        {
            System.out.println("Mail Sending Exception ---> "+e);
            throw new CustomException("Error While Sending Mail");
        }
    }

    public String emailBuilder(String name , String userName)
    {
//        String link = "http://localhost:3000/admin/sendmail?confirm="+token;
        String content = "<html><body>" +
                "<div> Hi "+ name +"</div>"+
                "<div>Password Reset Successfully Completed for Username : "+ userName +"</div></body></html>";
        return content;
    }

    public Response sendResetPassWordMailToEmployee(int emp_id)
    {
        repository.findById(emp_id).ifPresent(
                employee ->
                {
                    sendEmail(employee.getEmail(),emailBuilder(employee.getName(),
                            employee.getUserName()));
                }
        );
        return new Response("Email Send Successfully", HttpStatus.OK);
    }

    public Response sendForgotPassWordMailToEmployee(int id , String randomPassWord)
    {
        String toEmail = repository.findById(id).get().getUserName();
        String content = "<html><body>" +
                "<div> Hi "+ toEmail +"</div>"+
                "<div>Here is the Auto Generated Password : "+ randomPassWord +"</div>" +
                "<div>Kindly Change the password if you need in the Specified Link</div>"+
                "</body></html>";
        sendEmail(toEmail,content);
        return new Response("Email Send Successfully", HttpStatus.OK);
    }
}
