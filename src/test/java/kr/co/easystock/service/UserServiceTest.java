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
        User info = userService.getMyInfo("testman", "1234");

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
                .id(user.getId())
                .password("1541")
                .businessNo("5432154321")
                .name("험실")
                .email("testman@gmail.com")
                .phone("010-8765-4321")
                .address("인천")
                .build();

        // when
        boolean result = userService.changeMyInfo(userDto2);

        // then
        Assertions.assertThat(true).isEqualTo(result);
        Assertions.assertThat(user.getId()).isEqualTo(userDto2.getId());
        Assertions.assertThat(user.getPassword()).isEqualTo(userDto2.getPassword());
        Assertions.assertThat(user.getBusinessNo()).isEqualTo(userDto2.getBusinessNo());
        Assertions.assertThat(user.getName()).isEqualTo(userDto2.getName());
        Assertions.assertThat(user.getEmail()).isEqualTo(userDto2.getEmail());
        Assertions.assertThat(user.getPhone()).isEqualTo(userDto2.getPhone());
        Assertions.assertThat(user.getAddress()).isEqualTo(userDto2.getAddress());
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
                .id("testman")
                .password("1234")
                .businessNo("1234512345")
                .name("실험")
                .email("testman@naver.com")
                .phone("010-1234-5678")
                .address("서울")
                .build();

        return userDto;
    }
}
