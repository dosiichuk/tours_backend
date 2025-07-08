package com.tours.backend.services;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final JdbcTemplate jdbcTemplate;

    public void auditFrankfurterApiCall(String baseCurrency) {
        jdbcTemplate.update(
            "INSERT INTO frankfurter_api_audit (base_currency) VALUES (?)",
            baseCurrency
        );
    }

    public void auditEmailSent(Long userId, String email, String subject, String message) {
        jdbcTemplate.update(
                "INSERT INTO email_scheduler_audit (user_id, email, subject, message) VALUES (?, ?, ?, ?)",
                userId,
                email,
                subject,
                message);
    }

    public void auditUserLogin(Long id, String email) {
        jdbcTemplate.update(
            "INSERT INTO user_login_audit (user_id, email) VALUES (?, ?)",
            id,
            email
        );
    }

    public void auditWeatherApiCall(String location) {
        jdbcTemplate.update(
            "INSERT INTO weather_api_audit (location) VALUES (?)",
            location
        );
    }

    public void auditError(String errorType, String errorMessage) {
    jdbcTemplate.update(
        "INSERT INTO error_audit (error_type, error_message) VALUES (?, ?)",
        errorType,
        errorMessage
    );
}

}
