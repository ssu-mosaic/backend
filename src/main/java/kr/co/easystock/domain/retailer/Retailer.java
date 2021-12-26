package kr.co.easystock.domain.retailer;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Retailer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    @ManyToOne
    @JoinColumn(name = "business_no")
    private User user;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String memo;

    @Builder
    public Retailer(User user, String name, String email, String phone, String address, String memo)
    {
        this.user = user;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
    }

    public void update(String name, String email, String phone, String address, String memo)
    {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.memo = memo;
    }
}
