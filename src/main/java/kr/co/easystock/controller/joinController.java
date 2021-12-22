package kr.co.easystock.controller;

import kr.co.easystock.Dto.UserJoinRequestDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.UserJoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class joinController {

    private final UserJoinService userjoinService;

    @GetMapping(value = "/join")
    public String createJoinForm() {
        return "join";
    }

    @PostMapping(value = "/join")
    public int joinMember(@RequestBody UserJoinRequestDto requestDto) {

        return userjoinService.join(requestDto);
    }

}