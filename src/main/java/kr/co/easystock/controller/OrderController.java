package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.OrderDto;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.OrderService;
import kr.co.easystock.service.RetailerService;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
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

        Retailer retailer = retailerService.view(orderRequestDto.getRetailerId());
        if(retailer == null)
            return false;

        orderRequestDto.setUser(user);
        orderRequestDto.setRetailer(retailer);
        return orderService.add(orderRequestDto);
    }

    @PostMapping("/order/list")
    public List<OrderDto.OrderListResponseDto> getOrderList(@RequestBody Map<String, String> param)
    {
        List<OrderDto.OrderListResponseDto> orderListResponseDtoList = new ArrayList<>();
        User user = userService.getUser(param.get("userName"));
        if(user == null)
            return orderListResponseDtoList;

        orderListResponseDtoList = orderService.getOrderList(user);
        return orderListResponseDtoList;
    }
}
