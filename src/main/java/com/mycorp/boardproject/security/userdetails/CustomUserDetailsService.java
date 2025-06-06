package com.mycorp.boardproject.security.userdetails;

import com.mycorp.boardproject.member.domain.Member;
import com.mycorp.boardproject.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to load user-specific data during authentication.
 * Implements UserDetailsService to integrate with Spring Security.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Loads the user by username (email in this case).
     * Throws UsernameNotFoundException if user not found.
     *
     * @param username the email of the user trying to authenticate
     * @return UserDetails implementation (MemberDetails)
     * @throws UsernameNotFoundException if user not found
     */
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return new MemberDetails(member);
    }
}
