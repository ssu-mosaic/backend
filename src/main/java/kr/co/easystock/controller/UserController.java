package kr.co.easystock.controller;

import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/login")
    public String login(String email, String password, HttpSession session)
    {
        User user = userService.login(email, password);
        if(user == null)
        {
            System.out.println("login fail");
            return "redirect:/login";
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        session.removeAttribute("user");
        return "redirect:/";
    }
}
