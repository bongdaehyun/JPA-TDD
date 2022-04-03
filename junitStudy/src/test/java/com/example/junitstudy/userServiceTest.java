package com.example.junitstudy;

import api.domain.entity.User;
import api.domain.repository.userRepository;
import api.dto.UserReq;

import api.service.UserService;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

//참고 https://galid1.tistory.com/772
    @Mock
    private UserService userService;
    @Mock
    private userRepository userRepository;


    @Test
    public void createUser(){

        //given
        UserReq req= UserReq.builder().name("홍길동").password("1234").build();
        Long fakeId=1l;
        User user=req.toEntity();
        assertNotNull(userService);
        //mocking
        given(userService.insertUser(req)).willReturn(1L);
        given(userService.findUser(any())).willReturn(Optional.ofNullable(user));
        //when
        Long userid=userService.insertUser(req);

        //then
        User findUser=userService.findUser(userid).get();

        assertEquals(req.getName(),findUser.getName());
        assertEquals(req.getPassword(),findUser.getPassword());
    }
}
