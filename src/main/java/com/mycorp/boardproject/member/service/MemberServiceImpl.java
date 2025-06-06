package com.mycorp.boardproject.member.service;

import com.mycorp.boardproject.member.domain.Member;
import com.mycorp.boardproject.member.domain.Role;
import com.mycorp.boardproject.member.dto.MemberRegisterDto;
import com.mycorp.boardproject.member.dto.MemberResponseDto;
import com.mycorp.boardproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service implementation for handling member-related business logic.
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new member, encodes the password, and saves the entity.
     */
    @Override
    @Transactional
    public MemberResponseDto register(MemberRegisterDto registerDto) {
        Member member = Member.builder()
                .firstName(registerDto.getFirstName())
                .lastName(registerDto.getLastName())
                .email(registerDto.getEmail())
                .password(passwordEncoder.encode(registerDto.getPassword()))
                .address(registerDto.getAddress())
                .role(Role.ROLE_USER)
                .build();

        memberRepository.save(member);
        return MemberResponseDto.fromEntity(member);
    }

    /**
     * Retrieves member data by email. Throws an exception if not found.
     */
    @Override
    @Transactional(readOnly = true)
    public MemberResponseDto getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with email: " + email));
        return MemberResponseDto.fromEntity(member);
    }
}
