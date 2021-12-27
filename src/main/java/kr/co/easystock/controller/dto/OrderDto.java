package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

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
                    .detail(orderDetail)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class OrderListResponseDto
    {
        private String orderDetail;
        private LocalDateTime orderDate;
        private String retailerName;
        private String retailerPhone;

        @Builder
        public OrderListResponseDto(String orderDetail, LocalDateTime orderDate, String retailerName, String retailerPhone)
        {
            this.orderDetail = orderDetail;
            this.orderDate = orderDate;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
        }
    }
}
