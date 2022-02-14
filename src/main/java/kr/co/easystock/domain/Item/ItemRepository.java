package kr.co.easystock.domain.Item;

import kr.co.easystock.domain.retailer.Retailer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item>
{
    List<Item> findAllByRetailer(Retailer retailer, Pageable pageable);
}
