package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Integer>, JpaSpecificationExecutor<Stock>
{
    List<Stock> findAllByUser(User user, Pageable pageable);
}
