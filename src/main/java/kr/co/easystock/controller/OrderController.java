package kr.co.easystock.controller;

import kr.co.easystock.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @GetMapping("/order")
    public String order()
    {
        return "order";
    }
}
