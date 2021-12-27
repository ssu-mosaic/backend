package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

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
        private String detail;

        public OrderListResponseDto(String detail)
        {
            this.detail = detail;
        }
    }
}
