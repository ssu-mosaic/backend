package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto
{
    @Getter
    public static class UserRegisterRequestDto
    {
        private String id;
        private String password;
        private String name;
        private String businessNo;
        private String email;
        private String address;
        private String phone;

        @Builder
        public UserRegisterRequestDto(String id, String name, String password, String businessNo, String email, String address, String phone)
        {
            this.id = id;
            this.name = name;
            this.password = password;
            this.businessNo = businessNo;
            this.email = email;
            this.address = address;
            this.phone = phone;
        }

        public User toEntity() {
            return User.builder()
                    .id(id)
                    .name(name)
                    .password(password)
                    .businessNo(businessNo)
                    .email(email)
                    .address(address)
                    .phone(phone)
                    .build();
        }
    }

    @Getter
    public static class UserInfoDto
    {
        private String id;
        private String password;
        private String businessNo;
        private String name;
        private String email;
        private String address;
        private String phone;

        public UserInfoDto(User user)
        {
            this.id = user.getId();
            this.password = user.getPassword();
            this.businessNo = user.getBusinessNo();
            this.name = user.getName();
            this.email = user.getEmail();
            this.address = user.getAddress();
            this.phone = user.getPhone();
        }
    }

    @Getter
    public static class UserUpdateRequestDto
    {
        private String id;
        private String password;
        private String businessNo;
        private String name;
        private String email;
        private String address;
        private String phone;

        @Builder
        public UserUpdateRequestDto(String id, String name, String password, String businessNo, String email, String address, String phone)
        {
            this.id = id;
            this.name = name;
            this.password = password;
            this.businessNo = businessNo;
            this.email = email;
            this.address = address;
            this.phone = phone;
        }

        public User toEntity()
        {
            return User.builder()
                    .id(id)
                    .name(name)
                    .password(password)
                    .businessNo(businessNo)
                    .email(email)
                    .address(address)
                    .phone(phone)
                    .build();
        }
    }
}
