package kr.co.easystock.domain.Item;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.retailer.Retailer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Item extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Retailer retailer;

    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private String category;

    @Builder
    public Item(Retailer retailer, String name, int price, String detail, String category)
    {
        this.retailer = retailer;
        this.name = name;
        this.price = price;
        this.detail = detail;
        this.category = category;
    }
}
