package com.example.junitstudy.Service;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.domain.repository.UserRepository;
import com.example.junitstudy.api.dto.UserReq;

import com.example.junitstudy.api.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

//참고 https://galid1.tistory.com/772
    @InjectMocks //  @Mock(또는 @Spy) 주석으로 만든 모형을 이 인스턴스에 삽입
    private UserServiceImpl userService;

    @Mock //가짜 객체
    private UserRepository userRepository;


    @Test
    public void createUser() throws Exception{

        //given
        UserReq req= UserReq.builder().name("홍길동").password("1234").build();
        User user=req.toEntity();

        Long fakeId=1l;
        ReflectionTestUtils.setField(user,"userNumber",fakeId);

        //mocking
        given(userRepository.save(any())).willReturn(user);
        given(userRepository.findById(fakeId)).willReturn(Optional.ofNullable(user));

        //when
        Long userid=userService.insertUser(req);

        //then
        User findUser=userRepository.findById(userid).get();

        assertEquals(user.getUserNumber(),findUser.getUserNumber());
        assertEquals(user.getName(),findUser.getName());
        assertEquals(user.getPassword(),findUser.getPassword());
    }
}
