package com.example.junitstudy.api.controller;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.dto.UserRep;
import com.example.junitstudy.api.dto.UserReq;
import com.example.junitstudy.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {


    private final UserService userService;

    //Postํ ๋๋ 201๋ก
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserReq userReq) throws Exception {
        userService.insertUser(userReq);
        return new ResponseEntity<User>(HttpStatus.CREATED);
    }

    @GetMapping("/retrieveAll")
    public ResponseEntity<?> retrieveUser() throws Exception {
        List<UserRep> list=userService.findAllUser();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/retrieve/{userId}")
    public ResponseEntity<?> retrieveUser(@PathVariable long userId ) throws Exception {
        UserRep user = userService.findUser(userId);

        return ResponseEntity.status(200).body(user);
    }

}
