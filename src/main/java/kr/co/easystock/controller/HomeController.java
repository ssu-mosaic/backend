package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.StatsDto;
import kr.co.easystock.service.HomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static kr.co.easystock.controller.dto.StatsDto.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-28
 * Time: 오후 2:03
 */

@RestController
@RequiredArgsConstructor
public class HomeController
{
    private final HomeService homeService;

    /**
     * 메인페이지 통계
     * @param param
     * @return StatsDto
     */
    @PostMapping("/stats")
    public Result stats(@RequestBody Map<String, String> param)
    {
        return new Result(homeService.stats(param.get("userId")));
    }
}
