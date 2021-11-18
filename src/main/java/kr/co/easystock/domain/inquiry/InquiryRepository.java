package kr.co.easystock.domain.inquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>, JpaSpecificationExecutor<Inquiry>
{
}
