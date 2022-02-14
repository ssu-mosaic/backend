package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.ItemDto.*;
import static kr.co.easystock.controller.dto.UserDto.*;

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
        private String retailerDesc;

        public RetailerAddRequestDto(String userId, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerDesc)
        {
            this.userId = userId;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerDesc = retailerDesc;
        }

        public Retailer toEntity(User user)
        {
            return Retailer.builder()
                    .user(user)
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .desc(retailerDesc)
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
        private String retailerDesc;

        public RetailerUpdateRequestDto(String userId, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerDesc)
        {
            this.userId = userId;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerDesc = retailerDesc;
        }

        public Retailer toEntity()
        {
            return Retailer.builder()
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .desc(retailerDesc)
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
        private String retailerDesc;
        private List<ItemListDto> items;

        public RetailerViewDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.user = new UserInfoDto(entity.getUser());
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerAddress = entity.getAddress();
            this.retailerDesc = entity.getDesc();
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
        private String retailerDesc;
        private int itemNum;

        public RetailerListDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerAddress = entity.getAddress();
            this.retailerDesc = entity.getDesc();
            this.itemNum = entity.getItems().size();
        }
    }
}
