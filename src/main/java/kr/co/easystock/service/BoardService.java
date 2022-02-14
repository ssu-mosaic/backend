package kr.co.easystock.service;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.answer.AnswerRepository;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.easystock.controller.dto.AnswerDto.*;
import static kr.co.easystock.controller.dto.InquiryDto.*;

@RequiredArgsConstructor
@Service
public class BoardService
{
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;

    /**
     * 문의 작성
     * @param requestDto
     * @return Inquiry
     */
    @Transactional
    public Inquiry writeInquiry(InquiryWriteRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면??
         */

        Inquiry inquiry = inquiryRepository.save(requestDto.toEntity(user));

        return inquiry;
    }

    /**
     * 문의 수정
     * @param id
     * @return boolean
     */
    @Transactional
    public boolean updateInquiry(Long id, InquiryUpdateRequestDto requestDto)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        if(inquiry == null)
            return false;

        inquiry.update(requestDto.toEntity());
        return true;
    }

    /**
     * 문의글 보기
     * @param id
     * @return Inquiry
     */
    @Transactional(readOnly = true)
    public Inquiry viewInquiry(Long id)
    {
        return inquiryRepository.findById(id).orElse(null);
    }

    /**
     * 문의글 목록 보기
     * @param pageable
     * @return List
     */
    public List<InquiryListDto> viewInquiryList(Pageable pageable)
    {
        return inquiryRepository.findAll(pageable)
                .map(InquiryListDto::new)
                .getContent();
    }

    /**
     * 문의 삭제
     * @param id
     * @return boolean
     */
    @Transactional
    public boolean deleteInquiry(Long id)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        if(inquiry == null)
            return false;

        inquiry.delete();
        return true;
    }

    /**
     * 답변 작성
     * @param id
     * @param requestDto
     * @return Answer
     */
    @Transactional
    public Answer writeAnswer(Long id, AnswerWriteRequestDto requestDto)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        /*
        문의가 없으면??
         */

        Answer answer = answerRepository.save(requestDto.toEntity(inquiry));
        answer.answerToInquiry(inquiry);

        return answer;
    }

    /**
     * 답변 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @Transactional
    public boolean updateAnswer(Long id, AnswerUpdateRequestDto requestDto)
    {
        Answer answer = answerRepository.findById(id).orElse(null);
        if(answer == null)
            return false;

        answer.update(requestDto.getContent());
        return true;
    }
}
