package kr.co.easystock.domain.customer;

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
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private String phone;
    private String category;

    @Builder
    public Customer(User user, String name, String phone, String category)
    {
        this.user = user;
        this.name = name;
        this.phone = phone;
        this.category = category;
    }
}
