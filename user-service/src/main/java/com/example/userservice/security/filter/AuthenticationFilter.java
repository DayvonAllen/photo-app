package com.example.userservice.security.filter;

import com.example.userservice.api.v1.domain.LoginDto;
import com.example.userservice.api.v1.domain.UserDto;
import com.example.userservice.exceptions.UserException;
import com.example.userservice.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final Environment environment;


    public AuthenticationFilter(UserService userService, Environment environment, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.environment = environment;
        super.setAuthenticationManager(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            LoginDto creds = new ObjectMapper().readValue(request.getInputStream(), LoginDto.class);
            System.out.println("Security context before: " + SecurityContextHolder.getContext());
            Authentication auth = getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                    creds.getEmail(),
                    creds.getPassword(),
                    new ArrayList<>()));
            System.out.println("Authentication: " + auth);
            return auth;

        } catch (IOException e){
            throw new UserException("test exception");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String userName = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDetails = userService.getUserDetailsByEmail(userName);

        //generates JWT
        String token = Jwts.builder()
                //uses the user id as the subject in case you want to check whether the token belongs to this particular user
                        .setSubject(userDetails.getId().toString())
                //property needs to be in milliseconds
                        .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration"))))
                        .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                        .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDetails.getId().toString());

    }
}
