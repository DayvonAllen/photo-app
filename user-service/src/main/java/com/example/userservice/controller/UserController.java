package com.example.userservice.controller;

import com.example.userservice.api.v1.domain.UserDto;
import com.example.userservice.exceptions.UserException;
import com.example.userservice.security.filter.WebFilter;
import com.example.userservice.services.UserService;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users/")
public class
UserController {

    private final Environment environment;
    private final UserService userService;

    public UserController(Environment environment, UserService userService) {
        this.environment = environment;
        this.userService = userService;
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public String status(){
        return "Working on port " + environment.getProperty("local.server.port");
    }

    @PostMapping(value = "/registration", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public MappingJacksonValue createUser(@Valid @RequestBody UserDto user, Errors errors){
        if(errors.hasErrors()){
            throw new UserException(errors
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }
        WebFilter webFilter = new WebFilter();
        return webFilter.webFilter("BeanFilter", userService.createUser(user), "id", "firstName", "lastName", "email");
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public MappingJacksonValue getUserDetails(@PathVariable Long id){
        UserDto userDto = userService.getUserDetails(id);
        WebFilter webFilter = new WebFilter();
        return webFilter.webFilter("BeanFilter", userDto, "id", "firstName", "lastName", "email", "albums");
    }
}
