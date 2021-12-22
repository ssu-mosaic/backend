package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.customer.Customer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;
    private String name;
    private String detail;
    private String mainCategory;
    private String subCategory;
    private int amount;
}
