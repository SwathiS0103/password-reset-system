package com.example.auth;

import com.example.auth.service.ResetService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ResetServiceTest {

    private final ResetService resetService = new ResetService();

    @Test
    void testInvalidOtpVerification() {
        String email = "user@example.com";
        resetService.generateOtp(email);
        
        // We verify that a random wrong OTP returns 'false'
        boolean result = resetService.verifyOtp(email, "000000");
        assertFalse(result, "The system should reject an incorrect OTP!");
    }

    @Test
    void testNonExistentEmail() {
        // We verify that an email that never asked for an OTP returns 'false'
        boolean result = resetService.verifyOtp("notreal@example.com", "123456");
        assertFalse(result, "The system should reject emails that aren't in the system!");
    }
}
