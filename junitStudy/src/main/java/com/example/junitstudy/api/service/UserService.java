package com.example.junitstudy.api.service;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.dto.UserReq;

import java.util.Optional;

public interface UserService {

    Long insertUser(UserReq req)throws Exception;

    Optional<User> findUser(Long id);
}
