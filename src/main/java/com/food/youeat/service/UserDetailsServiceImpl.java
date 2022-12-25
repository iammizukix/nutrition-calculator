package com.food.youeat.service;

import com.food.youeat.entity.UserDetailsImpl;
import com.food.youeat.entity.UserEntity;
import com.food.youeat.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("user name not found. username = " + username));
        String password = user.getPassword();
        Collection<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(user.getRole()));
        return new UserDetailsImpl(username, password, authorities);
    }
}
