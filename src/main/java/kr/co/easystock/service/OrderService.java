package kr.co.easystock.service;

import kr.co.easystock.controller.dto.OrderDto;
import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

    public boolean add(OrderDto.OrderRequestDto orderRequestDto)
    {
        Order order = orderRepository.save(orderRequestDto.toEntity());
        if(order == null)
            return false;

        return true;
    }
}
