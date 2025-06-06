package com.mycorp.boardproject.member.controller;

import com.mycorp.boardproject.member.dto.MemberRegisterDto;
import com.mycorp.boardproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles user registration and login operations.
 */
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * Registers a new member.
     *
     * @param registerDto The registration request containing member details
     * @return A response indicating successful registration
     */
    @PostMapping("/register")
    public ResponseEntity<String> registerMember(@Valid @RequestBody MemberRegisterDto registerDto) {
        memberService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member registered successfully.");
    }

    /**
     * Login endpoint. This is a placeholder and typically handled by Spring Security.
     *
     * @return A message indicating the login endpoint was hit
     */
    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login endpoint (authentication is handled by Spring Security).");
    }
}
