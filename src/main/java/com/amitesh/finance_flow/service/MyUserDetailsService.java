package com.amitesh.finance_flow.service;

import com.amitesh.finance_flow.model.User;
import com.amitesh.finance_flow.repo.AuthRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AuthRepository authRepo;

    public MyUserDetailsService(AuthRepository authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = authRepo.findByUsername(username);

        if(user.isEmpty()){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }

        return new UserPrincipal(user.orElse(null));
    }
}
