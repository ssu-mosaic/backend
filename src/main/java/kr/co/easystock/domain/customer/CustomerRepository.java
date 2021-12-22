package kr.co.easystock.domain.customer;

import kr.co.easystock.domain.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, JpaSpecificationExecutor<Customer>
{
    List<Customer> findAllByUser(User user, Pageable pageable);
}
