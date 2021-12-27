package kr.co.easystock.domain.order;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.Item.Item;
import lombok.Getter;

import javax.persistence.*;

@Getter
public class OrderItem extends BaseTimeEntity
{
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice;
    private int count;
}
