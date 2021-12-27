package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class RetailerDto
{
    @Getter
    @Setter
    public static class RetailerAddRequestDto
    {
        private User user;
        private String userName;
        private String retailerName;
        private String retailerPhone;
        private String retailerEmail;
        private String retailerAddress;
        private String retailerMemo;

        public RetailerAddRequestDto(String userName, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerMemo)
        {
            this.userName = userName;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerMemo = retailerMemo;
        }

        public Retailer toEntity()
        {
            return Retailer.builder()
                    .user(user)
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .memo(retailerMemo)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class RetailerUpdateRequestDto
    {
        private User user;
        private String userName;
        private int retailerId;
        private String retailerName;
        private String retailerPhone;
        private String retailerEmail;
        private String retailerAddress;
        private String retailerMemo;

        public RetailerUpdateRequestDto(String userName, int retailerId, String retailerName, String retailerPhone, String retailerEmail, String retailerAddress, String retailerMemo)
        {
            this.userName = userName;
            this.retailerId = retailerId;
            this.retailerName = retailerName;
            this.retailerPhone = retailerPhone;
            this.retailerEmail = retailerEmail;
            this.retailerAddress = retailerAddress;
            this.retailerMemo = retailerMemo;
        }

        public Retailer toEntity()
        {
            return Retailer.builder()
                    .user(user)
                    .name(retailerName)
                    .phone(retailerPhone)
                    .email(retailerEmail)
                    .address(retailerAddress)
                    .memo(retailerMemo)
                    .build();
        }
    }

    @Getter
    public static class RetailerResponseDto
    {
        private int id;
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
    public static class RetailerListResponseDto
    {
        private int retailerId;
        private String retailerName;
        private String retailerEmail;
        private String retailerPhone;
        private String retailerAddress;
        private String retailerMemo;

        public RetailerListResponseDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerAddress = entity.getAddress();
            this.retailerMemo = entity.getMemo();
        }
    }
}
