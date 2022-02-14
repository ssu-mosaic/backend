package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemImg;
import kr.co.easystock.domain.retailer.Retailer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class ItemDto
{
    @Getter
    @Setter
    public static class ItemFormDto
    {
        private Retailer retailer;
        private String name;
        private int price;
        private String desc;
        private String category;

        @Builder
        public ItemFormDto(String name, int price, String desc, String category)
        {
            this.name = name;
            this.price = price;
            this.desc = desc;
            this.category = category;
        }

        public Item toEntity()
        {
            return Item.builder()
                    .retailer(retailer)
                    .name(name)
                    .price(price)
                    .desc(desc)
                    .category(category)
                    .build();
        }
    }

    @Getter
    public static class ItemListResponseDto
    {
        private Long id;
        private String name;
        private int price;

        public ItemListResponseDto(Item entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.price = entity.getPrice();
        }
    }

    @Getter
    @Setter
    public static class ItemImgDto
    {
        private int id;

        private String imgName;

        private String oriImgName;

        private String imgUrl;

        public ItemImgDto(ItemImg entity)
        {
            this.id = entity.getId();
            this.imgName = entity.getImgName();
            this.oriImgName = entity.getOriImgName();
            this.imgUrl = entity.getImgUrl();
        }
    }
}
