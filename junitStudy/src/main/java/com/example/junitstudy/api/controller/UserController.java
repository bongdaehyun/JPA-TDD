package com.example.junitstudy.api.controller;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.dto.UserReq;
import com.example.junitstudy.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {


    private final UserService userService;

    //Post할때는 201로
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserReq userReq) throws Exception {
        userService.insertUser(userReq);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

}
