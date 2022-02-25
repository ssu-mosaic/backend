package kr.co.easystock.domain.retailer;

import kr.co.easystock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RetailerRepository extends JpaRepository<Retailer, Long>, JpaSpecificationExecutor<Retailer>
{
    List<Retailer> findAllByUser(User user);
    Optional<Retailer> findByIdAndUser(Long id, User user);
}
