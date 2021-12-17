package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
public class User extends BaseTimeEntity
{
    @Id
    private String businessNo;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
