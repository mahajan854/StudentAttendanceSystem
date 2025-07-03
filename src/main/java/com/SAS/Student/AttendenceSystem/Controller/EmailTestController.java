package com.SAS.Student.AttendenceSystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")  // ✅ Different base path to avoid conflict
public class EmailTestController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/test")  // ✅ Final endpoint: /api/email/test
    public String testEmail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("your_receiver_email@gmail.com"); // 🔁 Replace with your actual email
        message.setSubject("Test Email from Spring Boot");
        message.setText("✅ This is a test email to confirm your email configuration.");

        try {
            mailSender.send(message);
            return "✅ Test email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to send email: " + e.getMessage();
        }
    }
}
