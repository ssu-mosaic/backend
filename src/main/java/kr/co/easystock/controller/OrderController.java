package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.OrderDto;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.OrderService;
import kr.co.easystock.service.RetailerService;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class OrderController
{
    private final UserService userService;
    private final RetailerService retailerService;
    private final OrderService orderService;

    @PostMapping("/order/add")
    public boolean add(@RequestBody OrderDto.OrderRequestDto orderRequestDto)
    {
        User user = userService.getUser(orderRequestDto.getUserName());
        if(user == null)
            return false;

        Retailer retailer = retailerService.getRetailer(orderRequestDto.getRetailerId());
        if(retailer == null)
            return false;

        orderRequestDto.setUser(user);
        orderRequestDto.setRetailer(retailer);
        return orderService.add(orderRequestDto);
    }
}
