package com.example.auth.controller;

import com.example.auth.service.ResetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class ResetController {

    private final ResetService resetService;

    public ResetController(ResetService resetService) {
        this.resetService = resetService;
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email) {
        resetService.generateOtp(email);
        return "OTP generated! Check your VS Code terminal to see the code.";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String email, @RequestParam String otp) {
        if (resetService.verifyOtp(email, otp)) {
            return "Success: OTP Verified!";
        } else {
            return "Error: Invalid OTP.";
        }
    }
}
