package kr.co.easystock.service;

import kr.co.easystock.controller.dto.UserDto;
import kr.co.easystock.controller.dto.UserJoinRequestDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService
{
    private final UserRepository userRepository;

    public User login(UserDto.UserLoginDto userLoginDto)
    {
        return userRepository.findByNameAndPassword(userLoginDto.getName(), userLoginDto.getPassword());
    }

    /* 회원 가입 */
    @Transactional
    public String register(UserDto.UserRegisterRequestDto userRegisterRequestDto) {
        validateDuplicateUser(userRegisterRequestDto);

        return userRepository.save(userRegisterRequestDto.toEntity()).getBusinessNo();
    }

    /* 중복 회원 체크 */
    private void validateDuplicateUser(UserDto.UserRegisterRequestDto requestDto) {
        userRepository.findByEmail(requestDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public User getUser(String name)
    {
        return userRepository.findByName(name);
    }
}
