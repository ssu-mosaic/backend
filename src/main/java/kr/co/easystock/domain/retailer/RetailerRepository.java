package kr.co.easystock.domain.retailer;

import kr.co.easystock.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RetailerRepository extends JpaRepository<Retailer, Long>, JpaSpecificationExecutor<Retailer>
{
    List<Retailer> findAllByUser(User user);
}
