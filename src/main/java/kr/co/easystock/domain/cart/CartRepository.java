package kr.co.easystock.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-12
 * Time: 오후 5:56
 */

@Repository
public interface CartRepository extends JpaRepository<Cart, String>, JpaSpecificationExecutor<Cart>
{
}
