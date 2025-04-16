package com.asthana.Dup_Notification.Controller;

import java.math.BigDecimal;

import jakarta.mail.MessagingException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asthana.Dup_Notification.Services.EmailService;

@RestController
@RequestMapping("/test")
public class TestEmailController {

    private final EmailService emailService;

    public TestEmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendTestEmail() {
        try {
            emailService.sentPaymentSuceessEmail("recipient@example.com", "Test User", new BigDecimal("100.00"), "ORDER123");
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send email: " + e.getMessage());
        }
    }
}
