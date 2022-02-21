package com.appcent.todo.jwt.security;


import com.appcent.todo.dao.UserDao;
import com.appcent.todo.entity.User;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {


    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findByUsername(username);
        User user = null;
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findById(id);
        User user = null;
        if(optionalUser.isPresent()){
            user = optionalUser.get();
        }
        return JwtUserDetails.create(user);
    }

}
