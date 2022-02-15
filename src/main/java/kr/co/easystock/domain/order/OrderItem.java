package kr.co.easystock.domain.order;

import kr.co.easystock.domain.Item.Item;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class OrderItem
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int totalPrice;
    private int count;
}
