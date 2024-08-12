package com.easyum.springsec.config.auth;

import com.easyum.springsec.entity.UserEntity;
import com.easyum.springsec.repo.UserRepo;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserEntityDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserEntityDetailsService(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            UserEntity candidate = userRepo.findByUsername(username);

            if (candidate != null) {
                return User.withUsername(candidate.getUsername())
                        .password(candidate.getPassword())
                        .roles("USER")
                        .build();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new UsernameNotFoundException(username);
    }
}
