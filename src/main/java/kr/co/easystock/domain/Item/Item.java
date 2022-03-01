package kr.co.easystock.domain.Item;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.stock.Stock;
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

    @OneToOne(mappedBy = "item", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Stock stock;

    private String name;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private String unit;

    @Column(nullable = false)
    private String detail;

    @Builder
    public Item(Retailer retailer, String name, int price, String unit, String detail)
    {
        this.retailer = retailer;
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.detail = detail;
    }

    public void update(Item item)
    {
        this.name = item.getName();
        this.price = item.getPrice();
        this.unit = item.getUnit();
        this.detail = item.getDetail();
    }

    public void mapRetailer(Retailer retailer)
    {
        this.retailer = retailer;
    }

    public void createItemToStock(Stock stock)
    {
        stock.mapItem(this);
        this.stock = stock;
    }

    public void unmapStock(Stock stock)
    {
        stock.mapItem(null);
        this.stock = null;
    }
}
