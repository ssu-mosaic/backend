package kr.co.easystock.domain.retailer;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String desc;

    @OneToMany(mappedBy = "retailer")
    private List<Item> items = new ArrayList<>();

    @Builder
    public Retailer(User user, String name, String email, String address, String phone, String desc)
    {
        this.user = user;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.desc = desc;
    }

    public void update(Retailer retailer)
    {
        this.name = retailer.getName();
        this.email = retailer.getEmail();
        this.address = retailer.getAddress();
        this.phone = retailer.getPhone();
        this.desc = retailer.getDesc();
    }

    // 연관관계 메서드
    public void addItem(Item item)
    {
        items.add(item);
        item.mapRetailer(this);
    }
}
