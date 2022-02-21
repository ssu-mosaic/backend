package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.ItemDto.ItemListDto;
import static kr.co.easystock.controller.dto.UserDto.UserInfoDto;

public class RetailerDto
{
    @Getter
    public static class RetailerAddRequestDto
    {
        private String userId;
        private String retailerName;
        private String retailerPhone;
        private String retailerEmail;
        private String retailerAddress;
        private String retailerDetail;

        public RetailerAddRequestDto(String userId, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerDetail)
        {
            this.userId = userId;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerDetail = retailerDetail;
        }

        public Retailer toEntity(User user)
        {
            return Retailer.builder()
                    .user(user)
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .detail(retailerDetail)
                    .build();
        }
    }

    @Getter
    public static class RetailerUpdateRequestDto
    {
        private String userId;
        private String retailerName;
        private String retailerPhone;
        private String retailerEmail;
        private String retailerAddress;
        private String retailerDetail;

        public RetailerUpdateRequestDto(String userId, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerDetail)
        {
            this.userId = userId;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerDetail = retailerDetail;
        }

        public Retailer toEntity()
        {
            return Retailer.builder()
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .detail(retailerDetail)
                    .build();
        }
    }

    @Getter
    public static class RetailerResponseDto
    {
        private Long id;
        private String name;
        private String phone;

        public RetailerResponseDto(Retailer entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.phone = entity.getPhone();
        }
    }

    @Getter
    public static class RetailerViewDto
    {
        private Long retailerId;
        private UserInfoDto user;
        private String retailerName;
        private String retailerEmail;
        private String retailerPhone;
        private String retailerAddress;
        private String retailerDetail;
        private List<ItemListDto> items;

        public RetailerViewDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.user = new UserInfoDto(entity.getUser());
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerAddress = entity.getAddress();
            this.retailerDetail = entity.getDetail();
            this.items = entity.getItems()
                    .stream()
                    .map(ItemListDto::new)
                    .collect(Collectors.toList());
        }
    }

    @Getter
    public static class RetailerListDto
    {
        private Long retailerId;
        private String retailerName;
        private String retailerEmail;
        private String retailerPhone;
        private String retailerAddress;
        private String retailerDetail;
        private int itemNum;

        public RetailerListDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerAddress = entity.getAddress();
            this.retailerDetail = entity.getDetail();
            this.itemNum = entity.getItems().size();
        }
    }
}
