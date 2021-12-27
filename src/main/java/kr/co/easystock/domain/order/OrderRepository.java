package kr.co.easystock.domain.order;

import kr.co.easystock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order>
{
    List<Order> findAllByUser(User user);
}
