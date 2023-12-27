package com.vivek.commerce.authuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class JpaUserDetailService implements UserDetailsService {

    private final JpaUserRepository jpaUserRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return jpaUserRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No User found with given email "+ email));
    }
}
