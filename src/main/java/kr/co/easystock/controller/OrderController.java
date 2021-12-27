package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.OrderDto;
import kr.co.easystock.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    @PostMapping("/order/add")
    public boolean add(@RequestBody OrderDto.OrderRequestDto orderRequestDto)
    {
        return orderService.add(orderRequestDto);
    }
}
