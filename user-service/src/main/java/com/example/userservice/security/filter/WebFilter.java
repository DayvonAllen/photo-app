package com.example.userservice.security.filter;

import com.example.userservice.api.v1.domain.UserDto;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public class WebFilter {
    public MappingJacksonValue webFilter(String name, UserDto userDto, String... fields){
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);
        FilterProvider filter = new SimpleFilterProvider().addFilter(name,simpleBeanPropertyFilter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userDto);
        mappingJacksonValue.setFilters(filter);
        return mappingJacksonValue;
    }
}
