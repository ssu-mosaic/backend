package kr.co.easystock.domain.Item;

import kr.co.easystock.domain.retailer.Retailer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Item
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String desc;

    @Column(nullable = false)
    private String category;

    @Builder
    public Item(Retailer retailer, String name, int price, String unit, String desc, String category)
    {
        this.retailer = retailer;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.desc = desc;
        this.category = category;
    }
}
