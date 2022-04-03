package api.service;

import api.domain.entity.User;
import api.dto.UserReq;

import java.util.Optional;

public interface UserService {

    Long insertUser(UserReq req)throws Exception;

    Optional<User> findUser(Long id);
}
