package kr.co.easystock.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2021-11-17
 * Time: 오후 3:15
 */

@Getter
@Setter
@Entity
public class User
{
    @Id
    private String email;
    private String password;
    private String businessNo;
    private String address;
    private String phone;
    private String gender;
    private LocalDateTime registerDate;
    private LocalDateTime expireDate;

}
