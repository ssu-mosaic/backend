package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderDto
{
    @Getter
    @Setter
    public static class OrderRequestDto
    {
        private User user;
        private String userName;
        private Retailer retailer;
        private int retailerId;
        private String orderDetail;

        public OrderRequestDto(String userName, String orderDetail, int retailerId)
        {
            this.userName = userName;
            this.orderDetail = orderDetail;
            this.retailerId = retailerId;
        }

        public Order toEntity()
        {
            return Order.builder()
                    .user(user)
                    .retailer(retailer)
                    .detail(orderDetail)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class OrderListResponseDto
    {
        private User user;
        private String userName;
        private LocalDateTime createdDate;

        public OrderListResponseDto()
        {

        }
    }
}
