package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.UserDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class UserController
{
    private final UserService userService;

    @GetMapping("/login")
    public String loginForm()
    {
        return "login";
    }

    @ResponseBody
    @PostMapping("/login")
    public boolean login(@RequestBody UserDto.UserLoginDto userLoginDto)
    {
        User user = userService.login(userLoginDto);
        if(user == null)
        {
            return false;
        }

        return true;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
