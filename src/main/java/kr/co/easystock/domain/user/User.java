package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity
{
    @Id
    @Column(length = 10)
    private String businessNo;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    private String phone;

    @Builder
    public User(String name, String password, String businessNo, String email, String address, String phone) {
        this.name = name;
        this.password = password;
        this.businessNo = businessNo;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    */
}
