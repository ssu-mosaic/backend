package kr.co.easystock.domain.retailer;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Retailer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String email;
    private String address;
    private String phone;
    private String memo;

    @Builder
    public Retailer(User user, String name, String email, String address, String phone, String memo)
    {
        this.user = user;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.memo = memo;
    }

    public void update(String name, String email, String address, String phone, String memo)
    {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.memo = memo;
    }
}
