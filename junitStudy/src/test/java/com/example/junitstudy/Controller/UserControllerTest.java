package com.example.junitstudy.Controller;

import com.example.junitstudy.api.controller.UserController;
import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.dto.UserRep;
import com.example.junitstudy.api.dto.UserReq;
import com.example.junitstudy.api.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//Http server를 실행하지 않고도 컨트롤러 테스트 가능, 주의: 서비스레이어는 배제
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserServiceImpl userService;

    ObjectMapper mapper=new ObjectMapper();

    private final UserReq userReq= UserReq.builder().name("진인호").password("1234").build();

    @Test
    @DisplayName("유저 생성 컨트롤러 테스트")
    public void createUser() throws Exception{

        //given
        given(userService.insertUser(any(UserReq.class))).willReturn(1L);

        //when
        mvc.perform(
                post("/api/create")
                        .content(mapper.writeValueAsString(userReq))
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                //then
                .andExpect(status().isCreated());

        //then == verify
        then(userService).should(times(1)).insertUser(any(UserReq.class));

    }
    @Test
    @DisplayName("모든 유저 조회 컨트롤러 테스트")
    public void retrieve() throws Exception{

        //given
        given(userService.findAllUser()).willReturn(List.of(new UserRep("홍길동","1234") , new UserRep("길동이","1234")));

        //when
        mvc.perform(
                    get("/api/retrieveAll")
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON)
                )
                .andDo(print())
                //then
                .andExpect(status().isOk());

        //then == verify
        then(userService).should(times(1)).findAllUser();
    }
    @Test
    @DisplayName("유저 조회 컨트롤러 테스트")
    public void retrieveUser() throws Exception{

        //given
        given(userService.findUser(1L)).willReturn(new UserRep("홍길동","1234"));

        //when
        mvc.perform(
                    get("/api/retrieve/1")
                    .contentType(APPLICATION_JSON)
                    .accept(APPLICATION_JSON)
                )
                .andDo(print())
                //then
                .andExpect(jsonPath("$.name").value("홍길동"))
                .andExpect(jsonPath("$.password").value("1234"))
                .andExpect(status().isOk());

        //then == verify
        then(userService).should().findUser(1L);

    }
}