package com.mycorp.boardproject.member.repository;

import com.mycorp.boardproject.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for Member entity.
 * Extends JpaRepository to provide CRUD operations.
 */
public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * Finds a member by their email.
     *
     * @param email the email of the member
     * @return Optional containing Member if found, empty otherwise
     */
    Optional<Member> findByEmail(String email);
}
