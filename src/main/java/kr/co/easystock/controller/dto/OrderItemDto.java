package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
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
        private Long orderProductId;
        private Long productId;
        private String productName;
        private String productUnit;
        private int productCnt;
        private String retailerName;

        public OrderItemListDto(OrderItem entity)
        {
            this.orderProductId = entity.getId();
            this.productCnt = entity.getCount();

            Item item = entity.getItem();
            if(item != null)
            {
                this.productId = item.getId();
                this.productName = item.getName();
                this.productUnit = item.getUnit();
                this.retailerName = item.getRetailer().getName();
            }
        }
    }
}
