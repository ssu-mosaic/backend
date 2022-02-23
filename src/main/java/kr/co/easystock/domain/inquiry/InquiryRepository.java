package kr.co.easystock.domain.inquiry;

import kr.co.easystock.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>, JpaSpecificationExecutor<Inquiry>
{
    Optional<Inquiry> findByIdAndDeletedDateIsNull(Long id);
    Optional<Inquiry> findByIdAndUserAndDeletedDateIsNull(Long id, User user);
    // 삭제된 문의를 제외하고 목록 조회
    List<Inquiry> findAllByUserAndDeletedDateIsNull(User user);
    List<Inquiry> findAllByDeletedDateIsNull();
}
