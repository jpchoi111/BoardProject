package com.mycorp.boardproject.member.service;

import com.mycorp.boardproject.member.dto.MemberRegisterDto;
import com.mycorp.boardproject.member.dto.MemberResponseDto;

/**
 * Service interface for handling member-related operations.
 */
public interface MemberService {

    /**
     * Registers a new member using the given request data.
     *
     * @param registerDto the member registration data
     * @return the response DTO of the registered member
     */
    MemberResponseDto register(MemberRegisterDto registerDto);

    /**
     * Retrieves member information by email.
     *
     * @param email the member's email
     * @return the response DTO of the found member
     */
    MemberResponseDto getMemberByEmail(String email);
}
