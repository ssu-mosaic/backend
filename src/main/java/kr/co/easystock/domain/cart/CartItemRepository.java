package kr.co.easystock.domain.cart;

import kr.co.easystock.domain.Item.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 5:35
 */

public interface CartItemRepository extends JpaRepository<CartItem, Long>, JpaSpecificationExecutor<CartItem>
{
    List<CartItem> findAllByCart(Cart cart);
    Optional<CartItem> findByCartAndItem(Cart cart, Item item);
}
