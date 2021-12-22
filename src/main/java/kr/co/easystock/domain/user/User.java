package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class User extends BaseTimeEntity
{
    @Id
    @Column(length = 10, columnDefinition = "char")
    private String businessNo;
    @Column(unique = true)
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    */
}
