package kr.co.easystock.Dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class UserJoinRequestDto {

    private String name;

    private String businessNo;

    private String email;

    private String password;

    private String address;

    private String phone;

    private String gender;

    @Builder
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

}
