package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserJoinRequestDto {

    private String name;

    private String businessNo;

    private String email;

    private String password;

    //private String address;

    //private String phone;

    //private String gender;

    @Builder
    public UserJoinRequestDto(String businessNo, String name, String email, String password)
    {
        this.businessNo = businessNo;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .email(email)
                .businessNo(businessNo)
                .password(password)
                .build();
    }

    /*
    public UserJoinRequestDto(String name, String password, String businessNo, String email, String address, String phone, String gender) {
        this.name = name;
        this.password = password;
        this.businessNo = businessNo;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .address(address)
                .email(email)
                .businessNo(businessNo)
                .gender(gender)
                .phone(phone)
                .password(password)
                .build();
    }
     */
}
