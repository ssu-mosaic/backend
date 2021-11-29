package kr.co.easystock.controller;

import kr.co.easystock.Dto.UserJoinRequestDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class joinController {

    private final UserJoinService userjoinService;

    @PostMapping(value = "/temporary resource/join")
    public String joinMember(@RequestBody UserJoinRequestDto requestDto) {
        User user = new User();

        userjoinService.join(requestDto);


        return "redirect:/";

    }

}