package kr.co.easystock.controller;

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
     * @param param
     * @return UserInfoDto
     */
    @PostMapping("/myinfo")
    public UserInfoDto getMyInfo(@RequestBody Map<String, String> param)
    {
        User user = userService.getMyInfo(param.get("userId"));
        if(user == null)
            return null;

        return new UserInfoDto(user);
    }

    /**
     * 내 정보 변경
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/myinfo/change")
    public boolean changeMyInfo(@RequestBody UserUpdateRequestDto requestDto)
    {
        return userService.changeMyInfo(requestDto);
    }

    /**
     * 비밀번호 변경
     * @param param
     * @return boolean
     */
    @PutMapping("/myinfo/changepwd")
    public boolean changeMyPwd(@RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        String userPwd = param.get("userPwd");

        return userService.changeMyPwd(userId, userPwd);
    }

    /**
     * 회원 탈퇴
     * @param param
     * @return boolean
     */
    @PutMapping("/withdraw")
    public boolean withdraw(@RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        return userService.withdraw(userId);
    }
}
