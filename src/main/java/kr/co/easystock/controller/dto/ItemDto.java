package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.customer.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ItemDto
{
    @Getter
    @Setter
    public static class ItemFormDto
    {
        private Customer customer;
        private String name;
        private int price;
        private String detail;
        private String category;

        @Builder
        public ItemFormDto(String name, int price, String detail, String category)
        {
            this.name = name;
            this.price = price;
            this.detail = detail;
            this.category = category;
        }

        public Item toEntity()
        {
            return Item.builder()
                    .customer(customer)
                    .name(name)
                    .price(price)
                    .detail(detail)
                    .category(category)
                    .build();
        }
    }

    @Getter
    public static class ItemListResponseDto
    {
        private int id;
        private String name;
        private int price;

        public ItemListResponseDto(Item entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.price = entity.getPrice();
        }
    }
}
