package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private String name;

    private String password;

    private String businessNo;

    private String email;

    private String address;

    private String phone;

    @Builder
    public UserJoinRequestDto(String name, String password, String businessNo, String email, String address, String phone)
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
