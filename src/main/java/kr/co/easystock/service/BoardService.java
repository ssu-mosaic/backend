package kr.co.easystock.service;

import kr.co.easystock.controller.dto.AnswerDto;
import kr.co.easystock.controller.dto.InquiryDto;
import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.answer.AnswerRepository;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BoardService
{
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    public int inquirySave(InquiryDto.InquiryFormDto inquiryFormDto)
    {
        Inquiry inquiry = inquiryRepository.save(inquiryFormDto.toEntity());

        return inquiry.getId();
    }

    public int answerSave(int id, AnswerDto.AnswerFormDto answerFormDto)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다."));
        answerFormDto.setInquiry(inquiry);

        Answer answer = answerRepository.save(answerFormDto.toEntity());

        return answer.getInquiryId();
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
