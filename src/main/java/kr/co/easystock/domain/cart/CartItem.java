package kr.co.easystock.domain.cart;

import kr.co.easystock.domain.Item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-12
 * Time: 오후 5:57
 */

@Getter
@NoArgsConstructor
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

    private int totalPrice;
    private int count;

    public CartItem(Cart cart, Item item, int count)
    {
        this.cart = cart;
        this.item = item;
        this.totalPrice = item.getPrice() * count;
        this.count = count;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return getId().equals(cartItem.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    public void update(int count)
    {
        this.totalPrice = item.getPrice() * count;
        this.count = count;
    }

    public void mapCart(Cart cart)
    {
        this.cart = cart;
    }
}
