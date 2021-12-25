package kr.co.easystock.service;

import kr.co.easystock.domain.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;

}
