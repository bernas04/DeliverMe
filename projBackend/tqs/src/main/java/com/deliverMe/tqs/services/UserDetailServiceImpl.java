package com.deliverMe.tqs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.deliverMe.tqs.TqsApplication;
import com.deliverMe.tqs.repository.PersonRepository;

public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PersonRepository userRepository;



    public UserDetailServiceImpl(PersonRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<com.deliverMe.tqs.model.Person> u = userRepository.findByEmail(email);
        if (u.isPresent()) {
            com.deliverMe.tqs.model.Person user = u.get();
            List<SimpleGrantedAuthority> l = new ArrayList<SimpleGrantedAuthority>();
            return new User(user.getEmail(),user.getPassword(),l);
        }
        throw new UsernameNotFoundException(email);
    }
    
    
}
