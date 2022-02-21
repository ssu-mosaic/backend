package kr.co.easystock.domain.notice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface NoticeRepository extends JpaRepository<Notice, Long>, JpaSpecificationExecutor<Notice>
{
    Optional<Notice> findByIdAndDeletedDateIsNull(Long id);
    // 삭제된 공지사항 제외하고 목록 조회
    List<Notice> findAllByDeletedDateIsNull();
}
