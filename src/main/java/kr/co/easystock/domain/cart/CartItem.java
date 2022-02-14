package kr.co.easystock.domain.cart;

import kr.co.easystock.domain.Item.Item;
import lombok.Getter;

import javax.persistence.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-12
 * Time: 오후 5:57
 */

@Getter
@Entity
public class CartItem
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}
