package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
//@Setter
@Entity
@Table
public class User extends BaseTimeEntity
{

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @Column
    private String name;

    @Column
    private String businessNo;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
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

}
