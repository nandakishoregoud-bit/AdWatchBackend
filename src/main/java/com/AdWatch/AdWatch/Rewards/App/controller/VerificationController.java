package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/users")
public class VerificationController {

    @Autowired
    private JavaMailSender emailSender;

    private final Map<String, String> verificationCodes = new HashMap<>(); // Temporary storage

    @PostMapping("/send-verification-code")
    public Map<String, String> sendVerificationCode(@RequestParam String email) {
        String code = generateVerificationCode();
        verificationCodes.put(email, code); // Save code with email as key
        
        // Send email to the user
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Your Verification Code");
        message.setText("Your verification code is: " + code);
        emailSender.send(message);

        // Return the code and a success message
        Map<String, String> response = new HashMap<>();
        response.put("message", "Verification code sent successfully");
        response.put("code", code);
        return response;
    }


    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        
    	System.out.printf(email,code);
    	String storedCode = verificationCodes.get(email);
        
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email); // Optionally remove the code after verification
            return "Verification successful!";
        } else {
            return "Invalid verification code.";
        }
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000)); // Generate a random 6-digit code
    }
}
