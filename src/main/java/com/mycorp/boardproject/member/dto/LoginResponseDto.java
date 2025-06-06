package com.mycorp.boardproject.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * DTO for login responses.
 * Contains the token and basic user information returned upon successful authentication.
 */
@Getter
@AllArgsConstructor
public class LoginResponseDto {

    // JWT token returned after successful login
    private String accessToken;

    // Optional: name or ID to display on frontend or for tracking
    private String memberName;

    // Optional: member's unique identifier
    private Long memberId;
}

