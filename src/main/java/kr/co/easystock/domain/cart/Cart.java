package kr.co.easystock.domain.cart;

import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User user;
}
