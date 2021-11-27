package kr.co.easystock.service;

import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;


public class MemberjoinService {

    private final UserRepository userRepository;

    public MemberjoinService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    /* 회원 가입 */
    public Long join(User user) {
        validateDuplicateUser(user);
        userRepository.save(user);

        return user.getId();
    }

    /* 중복 회원 체크 */
    private void validateDuplicateUser(User user) {
        userRepository.findByUserID(user.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
