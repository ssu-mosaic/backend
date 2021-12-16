package kr.co.easystock.service;

import kr.co.easystock.controller.dto.InquiryDTO;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2021-12-15
 * Time: 오후 4:59
 */

@RequiredArgsConstructor
@Service
public class BoardService
{
    private final InquiryRepository inquiryRepository;

    public int inquirySave(InquiryDTO.InquiryFormDTO inquiryFormDTO)
    {
        Inquiry inquiry = inquiryRepository.save(inquiryFormDTO.toEntity());
        return inquiry.getId();
    }
}
