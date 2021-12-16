package kr.co.easystock.service;

import kr.co.easystock.controller.dto.InquiryDto;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService
{
    private final InquiryRepository inquiryRepository;

    public int inquirySave(InquiryDto.InquiryFormDto inquiryFormDTO)
    {
        Inquiry inquiry = inquiryRepository.save(inquiryFormDTO.toEntity());
        return inquiry.getId();
    }

    public InquiryDto.InquiryResponseDto getInquiry(int id)
    {
        Inquiry entity = inquiryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));

        return new InquiryDto.InquiryResponseDto(entity);
    }

    public List<InquiryDto.InquiryListResponseDto> getInquiryList(Pageable pageable)
    {
        return inquiryRepository.findAll(pageable)
                .stream()
                .map(InquiryDto.InquiryListResponseDto::new)
                .collect(Collectors.toList());
    }
}
