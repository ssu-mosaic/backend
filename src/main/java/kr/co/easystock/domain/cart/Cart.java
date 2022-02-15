package kr.co.easystock.domain.cart;

import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-11
 * Time: 오후 9:48
 */

@Getter
@Entity
@NoArgsConstructor
public class Cart
{
    @Id
    @Column(updatable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @MapsId
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

    public void mapUser(User user)
    {
        this.user = user;
    }

    // 연관관계 메서드
    public void addCartItem(CartItem cartItem)
    {
        cartItems.add(cartItem);
        cartItem.mapCart(this);
    }

    // 연관관계 메서드
    public void deleteCartItem(CartItem cartItem)
    {
        cartItems.remove(cartItem);
        cartItem.mapCart(null);
    }
}
