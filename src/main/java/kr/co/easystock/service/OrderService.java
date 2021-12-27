package kr.co.easystock.service;

import kr.co.easystock.controller.dto.OrderDto;
import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.order.OrderRepository;
import kr.co.easystock.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

    public boolean add(@RequestBody OrderDto.OrderRequestDto orderRequestDto)
    {
        Order order = orderRepository.save(orderRequestDto.toEntity());
        if(order == null)
            return false;

        return true;
    }

    /*
    public List<OrderDto.OrderListResponseDto> getOrderList(User user)
    {
        return orderRepository.findAllByUser(user)
                .stream()
                .map(OrderDto.OrderListResponseDto::new)
                .collect(Collectors.toList());
    }
     */
}
