package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.cart.CartItem;
import lombok.Getter;

import static kr.co.easystock.controller.dto.ItemDto.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 5:06
 */

public class CartItemDto
{
    @Getter
    public static class CartItemAddRequestDto
    {
        private String userId;
        private Long productId;
        private int productCnt;

        public CartItemAddRequestDto(String userId, Long productId, int productCnt)
        {
            this.userId = userId;
            this.productId = productId;
            this.productCnt = productCnt;
        }

        public CartItem toEntity(Cart cart, Item item)
        {
            return new CartItem(cart, item, productCnt);
        }
    }

    @Getter
    public static class CartItemUpdateRequestDto
    {
        private int productCnt;

        public CartItemUpdateRequestDto(int productCnt)
        {
            this.productCnt = productCnt;
        }
    }

    @Getter
    public static class CartItemListDto
    {
        private Long productId;
        private String productName;
        private int productPrice;
        private String productUnit;
        private int productCnt;
        private String productDetail;

        public CartItemListDto(CartItem entity)
        {
            Item item = entity.getItem();
            if(item != null)
            {
                this.productId= item.getId();
                this.productName = item.getName();
                this.productPrice = item.getPrice();
                this.productUnit = item.getUnit();
                this.productCnt = entity.getCount();
                this.productDetail = item.getDetail();
            }
        }
    }
}
