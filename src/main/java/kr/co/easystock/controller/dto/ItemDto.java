package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemImg;
import kr.co.easystock.domain.retailer.Retailer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import static kr.co.easystock.controller.dto.RetailerDto.*;

public class ItemDto
{
    @Getter
    public static class ItemAddRequestDto
    {
        private Long retailerId;
        private String name;
        private int price;
        private String desc;
        private String category;
        private String unit;

        @Builder
        public ItemAddRequestDto(Long retailerId, String name, int price, String desc, String category, String unit)
        {
            this.retailerId = retailerId;
            this.name = name;
            this.price = price;
            this.desc = desc;
            this.category = category;
            this.unit = unit;
        }

        public Item toEntity(Retailer retailer)
        {
            return Item.builder()
                    .retailer(retailer)
                    .name(name)
                    .price(price)
                    .desc(desc)
                    .category(category)
                    .unit(unit)
                    .build();
        }
    }

    @Getter
    public static class ItemUpdateRequestDto
    {
        private Long retailerId;
        private String name;
        private int price;
        private String desc;
        private String category;
        private String unit;

        @Builder
        public ItemUpdateRequestDto(Long retailerId, String name, int price, String desc, String category, String unit)
        {
            this.retailerId = retailerId;
            this.name = name;
            this.price = price;
            this.desc = desc;
            this.category = category;
            this.unit = unit;
        }

        public Item toEntity()
        {
            return Item.builder()
                    .name(name)
                    .price(price)
                    .desc(desc)
                    .category(category)
                    .unit(unit)
                    .build();
        }
    }

    @Getter
    public static class ItemViewDto
    {
        private Long id;
        private RetailerViewDto retailer;
        private String name;
        private int price;
        private String desc;
        private String category;
        private String unit;

        public ItemViewDto(Item entity)
        {
            this.id = entity.getId();
            this.retailer = new RetailerViewDto(entity.getRetailer());
            this.name = entity.getName();
            this.price = entity.getPrice();
            this.desc = entity.getDesc();
            this.category = entity.getCategory();
            this.unit = entity.getUnit();
        }
    }

    @Getter
    public static class ItemListDto
    {
        private Long id;
        private String name;
        private int price;

        public ItemListDto(Item entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.price = entity.getPrice();
        }
    }

    @Getter
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
