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
        private Long itemId;
        private int count;

        public CartItemAddRequestDto(String userId, Long itemId, int count)
        {
            this.userId = userId;
            this.itemId = itemId;
            this.count = count;
        }

        public CartItem toEntity(Cart cart, Item item)
        {
            return new CartItem(cart, item, count);
        }
    }

    @Getter
    public static class CartItemUpdateRequestDto
    {
        private int count;

        public CartItemUpdateRequestDto(int count)
        {
            this.count = count;
        }
    }

    @Getter
    public static class CartItemListDto
    {
        private Long id;
        private ItemViewDto item;
        private int totalPrice;
        private int count;

        public CartItemListDto(CartItem entity)
        {
            this.id = entity.getId();
            this.item = new ItemViewDto(entity.getItem());
            int totalPrice = entity.getTotalPrice();
            this.count = entity.getCount();
        }
    }
}