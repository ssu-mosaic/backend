package kr.co.easystock.service;

import kr.co.easystock.controller.dto.UserJoinRequestDto;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@Service
public class UserJoinService {

    private final UserRepository userRepository;

    /* 회원 가입 */
    @Transactional
    public String join(UserJoinRequestDto requestDto) {
        validateDuplicateUser(requestDto);

        return userRepository.save(requestDto.toEntity()).getBusinessNo();
    }

    /* 중복 회원 체크 */
    private void validateDuplicateUser(UserJoinRequestDto requestDto) {
        userRepository.findByEmail(requestDto.getEmail())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }


}
