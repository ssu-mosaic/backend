package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String name;
    private String unit;
    private int count;

    @Builder
    public Stock(User user, Item item, String name, String unit, int count)
    {
        this.user = user;
        this.item = item;
        this.unit = unit;
        this.name = name;
        this.count = count;
    }

    public void update(String name, String unit, int count)
    {
        this.name = name;
        this.unit = unit;
        this.count = count;
    }

    // 비즈니스 로직 메서드
    public void addCount(int count)
    {
        this.count += count;
    }

    public void mapItem(Item item)
    {
        this.item = item;
    }
}
