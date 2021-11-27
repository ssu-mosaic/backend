package kr.co.easystock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2021-11-20
 * Time: 오후 5:21
 */

@Controller
public class IndexController
{
    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}
