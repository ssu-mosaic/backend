package kr.co.easystock.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-16
 * Time: 오후 2:57
 */

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>, JpaSpecificationExecutor<OrderItem>
{
}
