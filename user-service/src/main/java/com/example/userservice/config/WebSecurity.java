package com.example.userservice.config;

import com.example.userservice.security.filter.AuthenticationFilter;
import com.example.userservice.services.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(Environment environment, UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.environment = environment;
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //because we are using JSON web tokens
        http.csrf().disable();

        //only certain user can use(gateway)
        http.authorizeRequests().antMatchers("/api/v1/users/**").hasIpAddress(environment.getProperty("gateway.ip"))
                .and()
                .addFilter(getAuthenticationFilter());

        //so h2-console will work
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    //anyone can use
    //http.authorizeRequests().antMatchers("/api/v1/users/**").permitAll();
}

