package api.service;

import api.domain.entity.User;
import api.domain.repository.userRepository;
import api.dto.UserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service("userService")
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final userRepository userRepository;

    @Override
    public Long insertUser(UserReq req) {
        //return userRepository.save(req.toEntity()).getUserNumber();
        User user=User.builder().name(req.getName()).password(req.getPassword()).build();
        return userRepository.save(user).getUserNumber();
    }

    @Override
    public Optional<User> findUser(Long id) {
        return userRepository.findById(id);
    }

}
