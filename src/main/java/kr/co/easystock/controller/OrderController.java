package kr.co.easystock.controller;

import kr.co.easystock.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.OrderDto.*;

@RestController
@RequiredArgsConstructor
public class OrderController
{
    private final OrderService orderService;

    /**
     * 주문 추가
     * @param requestDto
     * @return String
     */
    @PostMapping("/order/checkout")
    public Long add(@RequestBody OrderAddRequestDto requestDto)
    {
        return orderService.add(requestDto).getId();
    }

    /**
     * 주문 수정
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/order/edit")
    public boolean update(@RequestBody OrderUpdateRequestDto requestDto)
    {
        return orderService.update(requestDto);
    }

    /**
     * 상품 주문 취소
     * @param orderItemId
     * @return boolean
     */
    @PutMapping("/order/cancel/{id}")
    public boolean cancel(@PathVariable(name = "id") Long orderItemId)
    {
        return orderService.cancel(orderItemId);
    }

    /**
     * 상품 수취 완료
     * @param orderItemId
     * @return boolean
     */
    @PutMapping("/order/complete/{id}")
    public boolean complete(@PathVariable(name = "id") Long orderItemId)
    {
        return orderService.complete(orderItemId);
    }

    /**
     * 주문 상세 조회
     * @param orderId
     * @return OrderViewDto
     */
    @GetMapping("/order/{id}")
    public OrderViewDto view(@PathVariable(name = "id") Long orderId)
    {
        return new OrderViewDto(orderService.view(orderId));
    }

    /**
     * 주문 목록 조회
     * @param param
     * @return List
     */
    @PostMapping("/order")
    public List<OrderListDto> list(@RequestBody Map<String, String> param)
    {
        return orderService.list(param.get("userId"))
                .stream()
                .map(OrderListDto::new)
                .collect(Collectors.toList());
    }
}
