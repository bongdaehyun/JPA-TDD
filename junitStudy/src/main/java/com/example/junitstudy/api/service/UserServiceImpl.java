package com.example.junitstudy.api.service;

import com.example.junitstudy.api.domain.entity.User;
import com.example.junitstudy.api.domain.repository.UserRepository;
import com.example.junitstudy.api.dto.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("userService")
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long insertUser(UserReq req)throws Exception {
        //중복 조회
        Optional<?> findUser=userRepository.findByName(req.getName());

        if(findUser.isPresent()){
            throw new IllegalAccessException("이미 중복된 유저입니다.");
        }

        User user=User.builder().userNumber(1L).name(req.getName()).password(req.getPassword()).build();
        return userRepository.save(user).getUserNumber();
    }

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

}
