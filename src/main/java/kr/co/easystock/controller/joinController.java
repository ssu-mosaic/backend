package kr.co.easystock.controller;

import kr.co.easystock.service.MemberjoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class joinController {

    private final MemberjoinService memberjoinService;

    @PostMapping("")

}
