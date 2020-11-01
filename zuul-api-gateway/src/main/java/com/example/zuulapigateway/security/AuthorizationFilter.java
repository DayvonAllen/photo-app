package com.example.zuulapigateway.security;

import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final Environment environment;

    public AuthorizationFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(environment.getProperty("auth.token.header.name"));

        if(authorizationHeader == null || !authorizationHeader.startsWith(environment.getProperty("auth.token.header.name.prefix"))){
            chain.doFilter(request, response);
            return;
        }

        System.out.println("Gateway security context before: " + SecurityContextHolder.getContext());
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("Gateway security context after: " + SecurityContextHolder.getContext());
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String authorizationHeader = request.getHeader(environment.getProperty("auth.token.header.name"));

        if(authorizationHeader == null){
            return null;
        }

        String token = authorizationHeader.replace(environment.getProperty("auth.token.header.name.prefix"), "");

        String userId = Jwts.parser()
                        .setSigningKey(environment.getProperty("token.secret"))
                        .parseClaimsJws(token)
                        .getBody()
                        .getSubject();

        if(userId == null){
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());
    }
}
