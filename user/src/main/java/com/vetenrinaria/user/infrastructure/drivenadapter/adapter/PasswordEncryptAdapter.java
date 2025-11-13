package com.vetenrinaria.user.infrastructure.drivenadapter.adapter;

import com.vetenrinaria.user.domain.model.gateway.PasswordEncoderGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordEncryptAdapter implements PasswordEncoderGateway {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(String password) {
        return this.bCryptPasswordEncoder.encode(password);
    }
}
