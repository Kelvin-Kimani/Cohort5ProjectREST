package com.cohort5projectrest.Security.Service;

import com.cohort5projectrest.Entities.User;
import com.cohort5projectrest.Repositories.UserRepository;
import com.cohort5projectrest.Security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = userRepository.findByEmployeeEmailAddress(emailAddress);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(user);

    }
}
