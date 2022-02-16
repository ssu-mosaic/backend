package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.order.OrderItem;
import lombok.Getter;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-16
 * Time: 오후 3:13
 */

public class OrderItemDto
{
    @Getter
    public static class OrderItemViewDto
    {

    }

    @Getter
    public static class OrderItemListDto
    {
        private Long orderItemId;
        private Long productId;
        private String productName;
        private String productUnit;
        private int productCnt;
        private String retailerName;

        public OrderItemListDto(OrderItem entity)
        {
            this.orderItemId = entity.getId();
            this.productId = entity.getItem().getId();
            this.productName = entity.getItem().getName();
            this.productUnit = entity.getItem().getUnit();
            this.productCnt = entity.getCount();
            this.retailerName = entity.getItem().getRetailer().getName();
        }
    }
}
