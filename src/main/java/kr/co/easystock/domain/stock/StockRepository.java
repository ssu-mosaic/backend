package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long>, JpaSpecificationExecutor<Stock>
{
    List<Stock> findAllByUser(User user);
    Optional<Stock> findByIdAndUser(Long id, User user);
    List<Stock> findTop5ByUserOrderByCountDesc(User user);
}
