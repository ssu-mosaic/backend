package kr.co.easystock.service;

import kr.co.easystock.controller.dto.UserDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService
{
    private final UserRepository userRepository;

    public User login(UserDto.UserLoginDto userLoginDto)
    {
        return userRepository.findByNameAndPassword(userLoginDto.getName(), userLoginDto.getPassword());
    }
}
