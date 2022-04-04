package com.example.junitstudy.api.dto;

import com.example.junitstudy.api.domain.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserReq {

    String name;
    String password;

    @Builder
    public UserReq(String name, String password){
       this.name=name;
       this.password=password;
    }

    public User toEntity(){
        return User.builder().name(name).password(password).build();
    }
}
