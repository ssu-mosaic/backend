package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.UserJoinRequestDto;
import kr.co.easystock.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class JoinController
{
    private final UserJoinService userjoinService;

    @GetMapping(value = "/join")
    public String createJoinForm() {
        return "join";
    }

    @PostMapping(value = "/join")
    public void joinMember(UserJoinRequestDto requestDto) {
        userjoinService.join(requestDto);
    }

    @ResponseBody
    @PostMapping(value = "/register")
    public String register(@RequestBody UserJoinRequestDto userJoinRequestDto)
    {
        return userjoinService.join(userJoinRequestDto);
    }
}
