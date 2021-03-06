package kr.co.easystock.service;

import kr.co.easystock.controller.dto.UserDto.UserRegisterRequestDto;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.user.Role;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import static kr.co.easystock.controller.dto.UserDto.*;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService
{
    private final UserRepository userRepository;

    /**
     * 회원 가입
     * @param requestDto
     * @return User
     */
    public User register(UserRegisterRequestDto requestDto)
    {
        // 유저 생성
        User user = requestDto.toEntity();
        validateDuplicateUser(user);

        // 장바구니 생성
        Cart cart = new Cart();
        user.assignCart(cart);

        return userRepository.save(user);
    }

    /**
     * 중복 회원 체크
     * @param user
     * @return
     */
    private void validateDuplicateUser(User user)
    {
        userRepository.findById(user.getId())
                .ifPresent(m ->
                {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 로그인
     * @param id
     * @param password
     * @return User
     */
    @Transactional(readOnly = true)
    public User login(String id, String password)
    {
        User user = userRepository.findByIdAndPasswordAndDeletedDateIsNull(id, password).orElse(null);

        return user;
    }

    /**
     * 관리자 로그인
     * @param id
     * @param password
     * @return User
     */
    public User adminLogin(String id, String password)
    {
        User user = userRepository.findByIdAndPasswordAndDeletedDateIsNull(id, password).orElse(null);
        if(user == null || user.getRole() == Role.MEMBER)
            return null;

        return user;
    }

    /**
     * 이메일로 아이디 찾기
     * @param email
     * @return
     */
    /*
    public String findIdByEmail(@RequestBody String email)
    {
        return userService.findIdByEmail(email);
    }
     */

    /**
     * 내 정보 조회
     * @param id
     * @return User
     */
    @Transactional(readOnly = true)
    public User getMyInfo(String id)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(id).get();

        return user;
    }

    /**
     * 내 정보 변경
     * @param requestDto
     * @return boolean
     */
    public boolean changeMyInfo(@RequestBody UserUpdateRequestDto requestDto)
    {
        User user = userRepository.findByIdAndPasswordAndDeletedDateIsNull(requestDto.getUserId(), requestDto.getUserPwd()).orElse(null);
        // 유저를 찾지 못했으면 false
        if(user == null)
            return false;

        user.update(requestDto.toEntity());
        return true;
    }

    /**
     * 비밀번호 변경
     * @param id
     * @param password
     * @return
     */
    public boolean changeMyPwd(String id, String password)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
        if(user == null)
            return false;

        user.updatePassword(password);
        return true;
    }

    //== 조회 메서드 ==
    @Transactional(readOnly = true)
    public User getUser(String name)
    {
        return userRepository.findByName(name).orElse(null);
    }

    /**
     * 회원 탈퇴
     * @param id
     * @return boolean
     */
    public boolean withdraw(String id)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
        if(user == null)
            return false;

        user.delete();
        return true;
    }
}
