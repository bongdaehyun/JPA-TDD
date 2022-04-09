package com.example.junitstudy.api.service;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.dto.UserRep;
import com.example.junitstudy.api.dto.UserReq;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Long insertUser(UserReq req)throws Exception;

    UserRep findUser(Long id);

    List<UserRep> findAllUser();
}
