package com.example.junitstudy.api.dto;

import com.example.junitstudy.api.domain.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class UserRep {

    String name;
    String password;

    public UserRep(String name,String password){
        this.name=name;
        this.password=password;

    }


}
