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
        private String retailerPhoneNo;
        private String retailerEmail;
        private String retailerDetail;

        public RetailerAddRequestDto(String userId, String retailerName, String retailerPhoneNo, String retailerEmail, String retailerDetail)
        {
            this.userId = userId;
            this.retailerName = retailerName;
            this.retailerPhoneNo = retailerPhoneNo;
            this.retailerEmail = retailerEmail;
            this.retailerDetail = retailerDetail;
        }

        public Retailer toEntity(User user)
        {
            return Retailer.builder()
                    .user(user)
                    .name(retailerName)
                    .phone(retailerPhoneNo)
                    .email(retailerEmail)
                    .detail(retailerDetail)
                    .build();
        }
    }

    @Getter
    public static class RetailerUpdateRequestDto
    {
        private String userId;
        private Long retailerId;
        private String retailerName;
        private String retailerPhoneNo;
        private String retailerEmail;
        private String retailerDetail;

        public RetailerUpdateRequestDto(String userId, Long retailerId, String retailerName, String retailerPhoneNo, String retailerEmail, String retailerDetail)
        {
            this.userId = userId;
            this.retailerId = retailerId;
            this.retailerName = retailerName;
            this.retailerPhoneNo = retailerPhoneNo;
            this.retailerEmail = retailerEmail;
            this.retailerDetail = retailerDetail;
        }

        public Retailer toEntity()
        {
            return Retailer.builder()
                    .name(retailerName)
                    .phone(retailerPhoneNo)
                    .email(retailerEmail)
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
        private String retailerName;
        private String retailerEmail;
        private String retailerPhoneNo;
        private String retailerDetail;

        public RetailerViewDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhoneNo = entity.getPhone();
            this.retailerDetail = entity.getDetail();
        }
    }

    @Getter
    public static class RetailerListDto
    {
        private Long retailerId;
        private String retailerName;
        private String retailerEmail;
        private String retailerPhone;
        private String retailerDetail;
        private int itemNum;

        public RetailerListDto(Retailer entity)
        {
            this.retailerId = entity.getId();
            this.retailerName = entity.getName();
            this.retailerEmail = entity.getEmail();
            this.retailerPhone = entity.getPhone();
            this.retailerDetail = entity.getDetail();
            this.itemNum = entity.getItems().size();
        }
    }
}
