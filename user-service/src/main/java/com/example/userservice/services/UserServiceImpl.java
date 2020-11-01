package com.example.userservice.services;

import com.example.userservice.api.v1.domain.AlbumDtoList;
import com.example.userservice.api.v1.domain.UserDto;
import com.example.userservice.api.v1.mapper.UserMapper;
import com.example.userservice.model.UserEntity;
import com.example.userservice.repos.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final Environment environment;
    private final AlbumServiceClient albumServiceClient;
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    public UserServiceImpl(UserMapper userMapper, UserRepo userRepo, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment, AlbumServiceClient albumServiceClient, RestTemplate restTemplate) {
        this.userMapper = userMapper;
        this.userRepo = userRepo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.environment = environment;
        this.albumServiceClient = albumServiceClient;
        this.restTemplate = restTemplate;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = userMapper.userDtoToUserMapper(userDto);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUserEntity = userRepo.save(userEntity);
        return userMapper.userToUserDtoMapper(savedUserEntity);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        return userMapper.userToUserDtoMapper(userRepo.findUserByEmail(email));
    }

    @Override
    public UserDto getUserDetails(Long id) {
        UserDto userDto = userMapper.userToUserDtoMapper(userRepo.findById(id).orElseThrow(NullPointerException::new));
        logger.info("Before calling albums service");
//        userDto.setAlbums(restTemplate.getForObject("api/v1/albums/" + id, AlbumDtoList.class));
        userDto.setAlbums(albumServiceClient.getAlbums(id));
        logger.info("After calling albums service");
        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepo.findUserByEmail(s);
        if(userEntity == null){
            logger.error("Username " + s + " is not found!");
            throw new UsernameNotFoundException(s);
        }
        return new User(userEntity.getEmail(), userEntity.getPassword(), true,true,true,true, new ArrayList<>());
    }
}
