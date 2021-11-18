package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    private String businessNo;
    private String email;
    private String password;
    private String address;
    private String phone;
    private String gender;

}
