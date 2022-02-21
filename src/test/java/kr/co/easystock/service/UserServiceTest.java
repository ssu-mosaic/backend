package kr.co.easystock.service;

import kr.co.easystock.controller.dto.UserDto.UserRegisterRequestDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static kr.co.easystock.controller.dto.UserDto.UserUpdateRequestDto;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-12
 * Time: 오후 6:33
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserServiceTest
{
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception
    {
        // given
        UserRegisterRequestDto userDto = createUser();

        // when
        User user = userService.register(userDto);

        // then
        Assertions.assertThat(user).isEqualTo(userRepository.findById(user.getId()).get());
    }

    @Test(expected = IllegalStateException.class)
    public void 중복회원_예외()
    {
        // given
        UserRegisterRequestDto user1 = createUser();
        UserRegisterRequestDto user2 = createUser();

        // when
        userService.register(user1);
        userService.register(user2);

        // then
        Assertions.fail("예외가 발생해야 합니다.");
    }

    @Test
    @Transactional(readOnly = true)
    public void 로그인()
    {
        // given
        UserRegisterRequestDto userDto = createUser();

        User user = userService.register(userDto);
        em.flush();

        // when
        User loginUser = userService.login("testman", "1234");

        // then
        Assertions.assertThat(loginUser).isEqualTo(user);
    }

    @Test
    @Transactional(readOnly = true)
    public void 이메일로비밀번호찾기()
    {
        // given

        // when

        // then
    }

    @Test
    @Transactional(readOnly = true)
    public void 패스워드찾기()
    {
        // given

        // when

        // then
    }

    @Test
    @Transactional(readOnly = true)
    public void 내정보보기()
    {
        // given
        UserRegisterRequestDto userDto = createUser();

        User user = userService.register(userDto);
        em.flush();

        // when
        User info = userService.getMyInfo("testman");

        // then
        Assertions.assertThat(info).isEqualTo(user);
    }

    @Test
    public void 내정보수정()
    {
        // given
        UserRegisterRequestDto userDto = createUser();

        User user = userService.register(userDto);
        em.flush();

        UserUpdateRequestDto userDto2 = UserUpdateRequestDto.builder()
                .userId(user.getId())
                .userPwd("1234")
                .userBusinessNo("5432154321")
                .userName("험실")
                .userEmail("testman@gmail.com")
                .userPhoneNo("010-8765-4321")
                .userAddress("인천")
                .build();

        // when
        boolean result = userService.changeMyInfo(userDto2);

        // then
        Assertions.assertThat(true).isEqualTo(result);
        Assertions.assertThat(user.getId()).isEqualTo(userDto2.getUserId());
        Assertions.assertThat(user.getPassword()).isEqualTo(userDto2.getUserPwd());
        Assertions.assertThat(user.getBusinessNo()).isEqualTo(userDto2.getUserBusinessNo());
        Assertions.assertThat(user.getName()).isEqualTo(userDto2.getUserName());
        Assertions.assertThat(user.getEmail()).isEqualTo(userDto2.getUserEmail());
        Assertions.assertThat(user.getPhone()).isEqualTo(userDto2.getUserPhoneNo());
        Assertions.assertThat(user.getAddress()).isEqualTo(userDto2.getUserAddress());
    }

    @Test
    public void 회원탈퇴()
    {
        // given
        UserRegisterRequestDto userDto = createUser();
        User user = userDto.toEntity();

        User dbUser = userService.register(userDto);
        em.flush();

        // when
        userService.withdraw(user.getId());

        // then
        Assertions.assertThat(user.getDeletedDate()).isNotEqualTo(dbUser.getDeletedDate());
    }

    private UserRegisterRequestDto createUser()
    {
        UserRegisterRequestDto userDto = UserRegisterRequestDto.builder()
                .userId("testman")
                .userPwd("1234")
                .userBusinessNo("1234512345")
                .userName("실험")
                .userEmail("testman@naver.com")
                .userPhoneNo("010-1234-5678")
                .userAddress("서울")
                .build();

        return userDto;
    }
}
