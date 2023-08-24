package com.naveen.jwt.controller;

import com.naveen.jwt.entity.AuthRequest;
import com.naveen.jwt.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WelcomeController {

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    @GetMapping("/")
    public String welcome() {
        return "Welcome to jwt sample !!";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(),
                            authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new BadCredentialsException("inavalid username/password");
        }
        return jwtUtil.generateToken(authRequest.getUserName());
    }
}
