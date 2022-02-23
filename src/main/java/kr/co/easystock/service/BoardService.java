package kr.co.easystock.service;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.answer.AnswerRepository;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import kr.co.easystock.domain.notice.Notice;
import kr.co.easystock.domain.notice.NoticeRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.easystock.controller.dto.AnswerDto.*;
import static kr.co.easystock.controller.dto.InquiryDto.*;
import static kr.co.easystock.controller.dto.NoticeDto.*;

@RequiredArgsConstructor
@Transactional
@Service
public class BoardService
{
    private final UserRepository userRepository;
    private final InquiryRepository inquiryRepository;
    private final AnswerRepository answerRepository;
    private final NoticeRepository noticeRepository;

    /**
     * 문의 작성
     * @param requestDto
     * @return Inquiry
     */
    public Inquiry writeInquiry(InquiryWriteRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면??
         */

        return inquiryRepository.save(requestDto.toEntity(user));
    }

    /**
     * 문의 수정
     * @param id
     * @return boolean
     */
    public boolean updateInquiry(Long id, InquiryUpdateRequestDto requestDto)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        if(inquiry == null)
            return false;

        inquiry.update(requestDto.toEntity());
        return true;
    }

    /**
     * 문의 상세 조회
     * @param id
     * @param userId
     * @return Inquiry
     */
    @Transactional(readOnly = true)
    public Inquiry viewInquiry(Long id, String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        return inquiryRepository.findByIdAndUserAndDeletedDateIsNull(id, user).orElse(null);
    }

    /**
     * 관리자 문의 상세 조회
     * @param id
     * @return Inquiry
     */
    public Inquiry adminViewInquiry(Long id)
    {
        return inquiryRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
    }

    /**
     * 1:1 문의 목록 조회
     * @param userId
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Inquiry> listInquiry(String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        return inquiryRepository.findAllByUserAndDeletedDateIsNull(user);
    }

    /**
     * 관리자 1:1 문의 목록 조회
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Inquiry> adminListInquiry()
    {
        return inquiryRepository.findAllByDeletedDateIsNull();
    }

    /**
     * 문의 삭제
     * @param id
     * @param userId
     * @return boolean
     */
    public boolean deleteInquiry(Long id, String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        Inquiry inquiry = inquiryRepository.findByIdAndUserAndDeletedDateIsNull(id, user).orElse(null);
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
    public Answer writeAnswer(Long id, AnswerWriteRequestDto requestDto)
    {
        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);
        /*
        문의가 없으면??
         */

        Answer answer = requestDto.toEntity(inquiry);
        // 양방향 연관관계의 편의 메서드 호출
        inquiry.answerToInquiry(answer);

        return answerRepository.save(answer);
    }

    /**
     * 답변 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    public boolean updateAnswer(Long id, AnswerUpdateRequestDto requestDto)
    {
        Answer answer = answerRepository.findById(id).orElse(null);
        if(answer == null)
            return false;

        answer.update(requestDto.getInquiryAnswer());
        return true;
    }

    /**
     * 공지 작성
     * @param requestDto
     * @return Notice
     */
    public Notice writeNotice(NoticeWriteRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면?
         */

        return noticeRepository.save(requestDto.toEntity(user));
    }

    /**
     * 공지 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    public boolean updateNotice(Long id, NoticeUpdateRequestDto requestDto)
    {
        Notice notice = noticeRepository.findById(id).orElse(null);
        if(notice == null)
            return false;

        notice.update(requestDto.getNoticeTitle(), requestDto.getNoticeContent());
        return true;
    }

    /**
     * 공지 상세 조회
     * @param id
     * @return Notice
     */
    @Transactional(readOnly = true)
    public Notice viewNotice(Long id)
    {
        return noticeRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
    }

    /**
     * 공지 목록 조회
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Notice> listNotice()
    {
        return noticeRepository.findAllByDeletedDateIsNull();
    }

    /**
     * 공지 삭제
     * @param id
     * @return boolean
     */
    public boolean deleteNotice(Long id)
    {
        Notice notice = noticeRepository.findByIdAndDeletedDateIsNull(id).orElse(null);
        if(notice == null)
            return false;

        notice.delete();
        return true;
    }
}
