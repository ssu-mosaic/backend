package kr.co.easystock.domain.customer;

import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Customer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto increment
    private int id;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private String name;
    private String category;
}
