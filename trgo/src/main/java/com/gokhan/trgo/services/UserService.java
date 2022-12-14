package com.gokhan.trgo.services;


import com.gokhan.trgo.entities.Role;
import com.gokhan.trgo.entities.User;
import com.gokhan.trgo.props.JWTLogin;
import com.gokhan.trgo.repositories.UserRepository;
import com.gokhan.trgo.utils.JwtUtil;

import com.gokhan.trgo.utils.REnum;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
@Transactional
public class UserService implements UserDetailsService {

    final UserRepository userRepository;
    final JwtUtil jwtUtil;
    final AuthenticationManager authenticationManager;
    public UserService(UserRepository userRepository, JwtUtil jwtUtil, @Lazy AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalJWTUser = userRepository.findByEmailEqualsIgnoreCase(username);
        if (optionalJWTUser.isPresent()) {
            User u = optionalJWTUser.get();
            UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                    u.getEmail(),
                    u.getPassword(),
                    u.isEnabled(),
                    u.isTokenExpired(),
                    true,
                    true,
                    roles(u.getRoles())
            );
            return userDetails;
        }else {
            throw new UsernameNotFoundException("B??yle bir kullan??c?? yok");
        }
    }


    public Collection roles(List<Role> rolex ) {
        List<GrantedAuthority> ls = new ArrayList<>();
        for ( Role role : rolex ) {
            ls.add( new SimpleGrantedAuthority( role.getName() ));
        }
        return ls;
    }

    public ResponseEntity register(User jwtUser) {
        Optional<User> optionalJWTUser = userRepository.findByEmailEqualsIgnoreCase(jwtUser.getEmail());
        Map<REnum, Object> hm = new LinkedHashMap();
        if ( !optionalJWTUser.isPresent() ) {
            jwtUser.setPassword(  encoder().encode( jwtUser.getPassword() )  );
            User user = userRepository.save(jwtUser);
            hm.put(REnum.status, true);
            hm.put(REnum.result, user);
            return new ResponseEntity( hm , HttpStatus.OK);
        }else {
            hm.put(REnum.status, false);
            hm.put(REnum.message, "Bu mail daha kay??t edilmi??");
            hm.put(REnum.result, jwtUser);
            return new ResponseEntity( hm, HttpStatus.NOT_ACCEPTABLE );
        }
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


    // aut
    // jwt almak i??in login i??lemi yaparak bu fonksiyon tetiklenmeldir.
    public ResponseEntity auth(JWTLogin jwtLogin) {
        Map<REnum, Object> hm = new LinkedHashMap<>();
        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                    jwtLogin.getUsername(), jwtLogin.getPassword()
            ) );
            UserDetails userDetails = loadUserByUsername(jwtLogin.getUsername());
            String jwt = jwtUtil.generateToken(userDetails);
            hm.put(REnum.status, true);
            hm.put( REnum.jwt, jwt );
            hm.put(REnum.result, userDetails);
            return new ResponseEntity(hm, HttpStatus.OK);
        }catch (Exception ex) {
            hm.put(REnum.status, false);
            hm.put( REnum.error, ex.getMessage() );
            return new ResponseEntity(hm, HttpStatus.NOT_ACCEPTABLE);
        }
    }

}
