package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;

    private String name;

    private String businessNo;

    @Column(length = 10, columnDefinition = "char")
    private String businessNo;
    
    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    private String phone;

    private String gender;

    @Builder
    public User(String name, String password, String businessNo, String email, String address, String phone, String gender) {
        this.name = name;
        this.password = password;
        this.businessNo = businessNo;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.gender = gender;
    }

    /*
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    */
}
