package kr.co.easystock.domain.Item;

import kr.co.easystock.domain.customer.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Integer>, JpaSpecificationExecutor<Item>
{
    List<Item> findAllByCustomer(Customer customer, Pageable pageable);
}
