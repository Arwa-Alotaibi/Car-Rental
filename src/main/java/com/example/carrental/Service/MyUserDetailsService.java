package com.example.carrental.Service;


import com.example.carrental.Model.MyUser;
import com.example.carrental.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final MyUserRepository myUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser=myUserRepository.findMyUsersByUsername(username);
        if (myUser==null){
            throw new UsernameNotFoundException("Wrong username or password");
        }
        return myUser;
    }

}

