package com.artostapyshyn.usermanagementapp.service.impl;

import com.artostapyshyn.usermanagementapp.entity.User;
import com.artostapyshyn.usermanagementapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User doesn't exists"));
        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(), Set.of(user.getRole()));
    }
}