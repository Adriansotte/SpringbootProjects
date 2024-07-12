package com.demo.security.service;

import com.demo.security.dao.request.SignUpRequest;
import com.demo.security.dao.request.SigninRequest;
import com.demo.security.dao.response.JwtAuthenticationResponse;

public interface AuthenticationService {
    JwtAuthenticationResponse signup(SignUpRequest request);

    JwtAuthenticationResponse signin(SigninRequest request);
}
