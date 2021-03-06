package com.example.junitstudy.Service;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.domain.repository.UserRepository;
import com.example.junitstudy.api.dto.UserRep;
import com.example.junitstudy.api.dto.UserReq;

import com.example.junitstudy.api.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class userServiceTest {

//참고 https://galid1.tistory.com/772
    @InjectMocks //  @Mock(또는 @Spy) 주석으로 만든 모형을 이 인스턴스에 삽입
    private UserServiceImpl userService;

    @Mock //가짜 객체
    private UserRepository userRepository;


    @Test
    @DisplayName("유저 생성 테스트")
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


    @Test
    @DisplayName("모든 유저 조회")
    public void retrieveAllUser() {
        //given
        UserReq req= UserReq.builder().name("홍길동").password("1234").build();
        User user=req.toEntity();
        Long fakeId=1l;
        ReflectionTestUtils.setField(user,"userNumber",fakeId);

        UserReq req1= UserReq.builder().name("길동이").password("1234").build();
        User user1=req.toEntity();

        ReflectionTestUtils.setField(user1,"userNumber",2L);

        given(userRepository.findAll()).willReturn(List.of(new User(1L,"홍길동","1234") , new User(2L,"길동이","1234")));

        //when
        List<UserRep> userList = userService.findAllUser();

        //then
        assertEquals(user.getName(),userList.get(0).getName());
        assertEquals(user1.getName(),userList.get(1).getName());
    }

    @Test
    @DisplayName("조건에 맞는 유저 조회")
    public void retrieveUser() {
        //given
        UserReq req= UserReq.builder().name("홍길동").password("1234").build();
        User user=req.toEntity();
        Long fakeId=1l;
        ReflectionTestUtils.setField(user,"userNumber",fakeId);

        given(userRepository.findById(any())).willReturn(Optional.of(user));

        //when
        UserRep finduser = userService.findUser(1L);

        //then
        assertEquals(user.getName(),finduser.getName());
        assertEquals(user.getPassword(),finduser.getPassword());

    }
}
