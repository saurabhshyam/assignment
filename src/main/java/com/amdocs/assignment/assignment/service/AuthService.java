package com.amdocs.assignment.assignment.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.amdocs.assignment.assignment.repository.UserRepository;
import com.amdocs.assignment.assignment.vo.UserAuthData;
@Service
public class AuthService implements UserDetailsService {
	@Autowired
	UserRepository repo;
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	   UserAuthData user = repo.findById(username).isPresent()?repo.findById(username).get():null;
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getName(), user.getPassword(), new ArrayList<GrantedAuthority>());
    }

}
