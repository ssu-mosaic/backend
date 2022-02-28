package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.OrderItemDto.*;

public class OrderDto
{
    @Getter
    public static class OrderAddRequestDto
    {
        private String userId;

        public OrderAddRequestDto() {}

        public OrderAddRequestDto(String userId)
        {
            this.userId = userId;
        }

        public Order toEntity(User user)
        {
            return new Order(user);
        }
    }

    @Getter
    public static class OrderUpdateRequestDto
    {
        public OrderUpdateRequestDto()
        {

        }
    }

    @Getter
    public static class OrderViewDto
    {
        private Long id;

        public OrderViewDto(Order entity)
        {
            this.id = entity.getId();
        }
    }

    @Getter
    public static class OrderListDto
    {
        private Long orderId;
        private List<OrderItemListDto> orderProducts;
        private LocalDateTime orderDate;

        public OrderListDto(Order entity)
        {
            this.orderId = entity.getId();
            this.orderProducts = entity.getOrderItems()
                    .stream()
                    .map(OrderItemListDto::new)
                    .collect(Collectors.toList());
            this.orderDate = entity.getCreatedDate();
        }
    }
}
