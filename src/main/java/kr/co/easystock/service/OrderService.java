package kr.co.easystock.service;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.cart.CartItem;
import kr.co.easystock.domain.cart.CartItemRepository;
import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.order.OrderItem;
import kr.co.easystock.domain.order.OrderItemRepository;
import kr.co.easystock.domain.order.OrderRepository;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.easystock.controller.dto.OrderDto.OrderAddRequestDto;
import static kr.co.easystock.controller.dto.OrderDto.OrderUpdateRequestDto;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderItemRepository orderItemRepository;

    /**
     * 주문 추가
     * @param requestDto
     * @return Order
     */
    public Order add(OrderAddRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면?
         */

        // 주문 객체 생성
        Order order = requestDto.toEntity(user);

        // 장바구니 목록을 불러온다.
        List<CartItem> cartItems = user.getCart().getCartItems();
        // 장바구니가 비었으면 주문 실패
        if(cartItems.size() == 0)
            return order;

        // 장바구니의 모든 물품을 주문한다.
        for(CartItem cartItem : cartItems)
        {
            Item item = cartItem.getItem();
            // 장바구니의 물품을 주문한다.
            order.addOrderItem(new OrderItem(order, item, cartItem.getCount()));
            // 장바구니에서 삭제
            cartItemRepository.delete(cartItem);
        }

        return orderRepository.save(order);
    }

    /**
     * 주문 수정
     * @param requestDto
     * @return boolean
     */
    public boolean update(OrderUpdateRequestDto requestDto)
    {
        return true;
    }

    /**
     * 상품 주문 취소
     * @param orderItemId
     * @return boolean
     */
    public boolean cancel(Long orderItemId)
    {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if(orderItem == null)
            return false;

        orderItem.cancel();
        return true;
    }

    /**
     * 상품 수취 완료
     * @param orderItemId
     * @return boolean
     */
    public boolean complete(Long orderItemId)
    {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElse(null);
        if(orderItem == null)
            return false;

        // 재고 수 증가
        orderItem.getItem().getStock().addCount(orderItem.getCount());
        // 완료 처리
        orderItem.complete();
        return true;
    }

    /**
     * 주문 상세 조회
     * @param orderId
     * @return Order
     */
    @Transactional(readOnly = true)
    public Order view(Long orderId)
    {
        return orderRepository.findById(orderId).orElse(null);
    }

    /**
     * 주문 목록 조회
     * @param userId
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Order> list(String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        /*
        user가 없으면?
         */

        return orderRepository.findAllByUser(user);
    }
}
