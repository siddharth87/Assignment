package com.example.assignment.controller;

import com.example.assignment.service.JwtTokenService;
import com.example.assignment.dto.AuthRequest;
import com.example.assignment.dto.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        try {
            log.info("Received login request for username {}", authRequest.getUsername());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            String token = jwtTokenService.generateToken(authentication);
            return ResponseEntity.ok(new AuthResponse(token, authRequest.getUsername(), "Bearer token created for user with privilege "+ authentication.getAuthorities().toString(), true));
        }catch (AuthenticationException e){
            log.warn("Unable to authenticate the credentials",e);
            return ResponseEntity.ok(new AuthResponse("N/A", authRequest.getUsername(), "Bad Credentials", false));
        }
        catch (Exception e){
            log.warn("Something went wrong in creating JWT token", e);
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}