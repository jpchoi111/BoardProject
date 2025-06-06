package com.mycorp.boardproject.member.dto;

import com.mycorp.boardproject.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

/**
 * DTO for exposing member information in API responses.
 * Keeps domain entities decoupled from the controller and external layers.
 */
@Getter
public class MemberResponseDto {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;

    @Builder
    public MemberResponseDto(Long id, String firstName, String lastName, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
    }

    /**
     * Factory method to convert Member entity to MemberResponseDto.
     *
     * @param member the Member entity to convert
     * @return a response DTO representing the member
     */
    public static MemberResponseDto fromEntity(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }
}
