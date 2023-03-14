package com.test.api.auth;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class RegisterRequest {
    private String first_name;
    private String last_name;
    private String mobile_phone;
    private String password;
}
