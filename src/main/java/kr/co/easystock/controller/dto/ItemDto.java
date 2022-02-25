package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemImg;
import kr.co.easystock.domain.retailer.Retailer;
import lombok.Builder;
import lombok.Getter;

public class ItemDto
{
    @Getter
    public static class ItemAddRequestDto
    {
        private Long retailerId;
        private String productName;
        private int productPrice;
        private String productDetail;
        private String productUnit;

        @Builder
        public ItemAddRequestDto(Long retailerId, String productName, int productPrice, String productDetail, String productUnit)
        {
            this.retailerId = retailerId;
            this.productName = productName;
            this.productPrice = productPrice;
            this.productDetail = productDetail;
            this.productUnit = productUnit;
        }

        public Item toEntity(Retailer retailer)
        {
            return Item.builder()
                    .retailer(retailer)
                    .name(productName)
                    .price(productPrice)
                    .detail(productDetail)
                    .unit(productUnit)
                    .build();
        }
    }

    @Getter
    public static class ItemUpdateRequestDto
    {
        private Long retailerId;
        private String productName;
        private int productPrice;
        private String productDetail;
        private String productUnit;

        @Builder
        public ItemUpdateRequestDto(Long retailerId, String productName, int productPrice, String productDetail, String productUnit)
        {
            this.retailerId = retailerId;
            this.productName = productName;
            this.productPrice = productPrice;
            this.productDetail = productDetail;
            this.productUnit = productUnit;
        }

        public Item toEntity()
        {
            return Item.builder()
                    .name(productName)
                    .price(productPrice)
                    .detail(productDetail)
                    .unit(productUnit)
                    .build();
        }
    }

    @Getter
    public static class ItemViewDto
    {
        private Long productId;
        private String productName;
        private int productPrice;
        private String productDetail;
        private String productUnit;

        public ItemViewDto(Item entity)
        {
            this.productId = entity.getId();
            this.productName = entity.getName();
            this.productPrice = entity.getPrice();
            this.productDetail = entity.getDetail();
            this.productUnit = entity.getUnit();
        }
    }

    @Getter
    public static class ItemListRequestDto
    {
        private String userId;
        private Long retailerId;

        public ItemListRequestDto(String userId, Long retailerId)
        {
            this.userId = userId;
            this.retailerId = retailerId;
        }
    }

    @Getter
    public static class ItemListDto
    {
        private Long productId;
        private String productName;
        private int productPrice;

        public ItemListDto(Item entity)
        {
            this.productId = entity.getId();
            this.productName = entity.getName();
            this.productPrice = entity.getPrice();
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
