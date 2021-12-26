package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto
{
    @Getter
    public static class UserLoginDto
    {
        private String name;
        private String password;

        public UserLoginDto(String name, String password)
        {
            this.name = name;
            this.password = password;
        }
    }

    @Getter
    public static class UserRegisterRequestDto
    {
        private String name;

        private String password;

        private String businessNo;

        private String email;

        private String address;

        private String phone;

        @Builder
        public UserRegisterRequestDto(String name, String password, String businessNo, String email, String address, String phone)
        {
            this.name = name;
            this.password = password;
            this.businessNo = businessNo;
            this.email = email;
            this.address = address;
            this.phone = phone;
        }

        public User toEntity() {
            return User.builder()
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
