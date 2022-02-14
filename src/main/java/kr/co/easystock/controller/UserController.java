package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.UserDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController
{
    private final UserService userService;

    @PostMapping("/register")
    public String register(@RequestBody UserDto.UserRegisterRequestDto requestDto)
    {
        return userService.register(requestDto).getId();
    }

    @PostMapping("/login")
    public UserDto.UserInfoDto login(@RequestBody String name, @RequestBody String password)
    {
        User user = userService.login(name, password);
        if(user == null)
            return null;

        return new UserDto.UserInfoDto(user);
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

    @PostMapping("/myinfo")
    public UserDto.UserInfoDto getMyInfo(@RequestBody String id, @RequestBody String password)
    {
        User user = userService.getMyInfo(id, password);
        if(user == null)
            return null;

        return new UserDto.UserInfoDto(user);
    }

    @PutMapping("/myinfo/change")
    public boolean changeMyInfo(@RequestBody UserDto.UserUpdateRequestDto requestDto)
    {
        return userService.changeMyInfo(requestDto);
    }

    @DeleteMapping("/withdraw")
    public boolean withdraw(@RequestBody String id)
    {
        return userService.withdraw(id);
    }
}
