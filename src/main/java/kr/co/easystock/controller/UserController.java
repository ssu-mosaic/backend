package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.UserDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static kr.co.easystock.controller.dto.UserDto.*;

@RequiredArgsConstructor
@RestController
public class UserController
{
    private final UserService userService;

    /**
     * 회원가입
     * @param requestDto
     * @return id
     */
    @PostMapping("/register")
    public String register(@RequestBody UserRegisterRequestDto requestDto)
    {
        return userService.register(requestDto).getId();
    }

    /**
     * 로그인
     * @param param
     * @return boolean
     */
    @PostMapping("/login")
    public boolean login(@RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        String userPwd = param.get("userPwd");
        User user = userService.login(userId, userPwd);
        if(user == null)
            return false;

        return true;
    }

    /*
    @PostMapping(value = "/findid")
    public String findIdByEmail(@RequestBody String email)
    {
        return userService.findIdByEmail(email);
    }
     */

    /*
    @PostMapping(value = "/findpwd")
    public String findPwd(@RequestBody String id)
    {
        return userService.findPwd(id);
    }
     */

    /**
     * 내 정보 조회
     * @param id
     * @param password
     * @return UserInfoDto
     */
    @PostMapping("/myinfo")
    public UserInfoDto getMyInfo(@RequestBody String id, @RequestBody String password)
    {
        User user = userService.getMyInfo(id, password);
        if(user == null)
            return null;

        return new UserInfoDto(user);
    }

    /**
     * 내 정보 수정
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/myinfo/change")
    public boolean changeMyInfo(@RequestBody UserUpdateRequestDto requestDto)
    {
        return userService.changeMyInfo(requestDto);
    }

    /**
     * 회원 탈퇴
     * @param id
     * @return boolean
     */
    @DeleteMapping("/withdraw")
    public boolean withdraw(@RequestBody String id)
    {
        return userService.withdraw(id);
    }
}
