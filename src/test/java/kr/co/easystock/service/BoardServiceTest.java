package kr.co.easystock.service;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.answer.AnswerRepository;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import kr.co.easystock.domain.notice.Notice;
import kr.co.easystock.domain.notice.NoticeRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.AnswerDto.AnswerUpdateRequestDto;
import static kr.co.easystock.controller.dto.AnswerDto.AnswerWriteRequestDto;
import static kr.co.easystock.controller.dto.InquiryDto.*;
import static kr.co.easystock.controller.dto.NoticeDto.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-13
 * Time: 오후 7:52
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest
{
    @Autowired
    private BoardService boardService;
    @Autowired
    private InquiryRepository inquiryRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 문의작성()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);

        // when
        Inquiry inquiry = boardService.writeInquiry(requestDto);
        em.flush();

        Inquiry findInquiry = inquiryRepository.findById(inquiry.getId()).orElse(null);

        // then
        Assertions.assertEquals(inquiry, findInquiry);
    }

    @Test
    public void 문의수정()
    {
        // given
        User user = createUser();
        Inquiry inquiry = boardService.writeInquiry(createInquiry(user));

        // when
        InquiryUpdateRequestDto requestDto = InquiryUpdateRequestDto.builder()
                .inquiryTitle("목제")
                .inquiryContent("용내")
                .build();

        boardService.updateInquiry(inquiry.getId(), requestDto);
        em.flush();

        // then
        Assertions.assertEquals(inquiry.getTitle(), requestDto.getInquiryTitle());
        Assertions.assertEquals(inquiry.getContent(), requestDto.getInquiryContent());
    }

    @Test
    public void 문의글보기()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(requestDto);

        // when
        Inquiry findInquiry = boardService.viewInquiry(inquiry.getId(), user.getId());

        // then
        Assertions.assertEquals(inquiry, findInquiry);
    }

    @Test
    public void 문의목록보기()
    {
        // given
        User user = createUser();
        for(int i=0; i<20; i++)
        {
            InquiryWriteRequestDto inquiry = createInquiry(user);
            boardService.writeInquiry(inquiry);
        }

        // when
        List<InquiryListDto> inquiryListDtos = boardService.listInquiry(user.getId())
                .stream()
                .map(InquiryListDto::new)
                .collect(Collectors.toList());

        // then
        for(InquiryListDto dto : inquiryListDtos)
            System.out.println(dto.getInquiryId() + " : " + dto.getInquiryTitle());

        Assertions.assertEquals(inquiryListDtos.size(), 20);
    }

    @Test
    public void 문의삭제()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(requestDto);

        // when
        boardService.deleteInquiry(inquiry.getId(), user.getId());

        // then
        Assertions.assertNotEquals(null, inquiry.getDeletedDate());
    }

    @Test
    public void 삭제된_문의_제외한_목록보기()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);
        for(int i=0; i<20; i++)
            boardService.writeInquiry(requestDto);

        // when
        boolean result1 = boardService.deleteInquiry(1L, user.getId());
        boolean result2 = boardService.deleteInquiry(2L, user.getId());
        List<InquiryListDto> inquiryListDtos = boardService.listInquiry(user.getId())
                .stream()
                .map(InquiryListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(true, result1);
        Assertions.assertEquals(true, result2);
        Assertions.assertEquals(18, inquiryListDtos.size());
    }

    @Test
    public void 답변작성()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto inquiryDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(inquiryDto);
        AnswerWriteRequestDto answerDto = createAnswer();

        // when
        Answer answer = boardService.writeAnswer(inquiry.getId(), answerDto);
        Answer findAnswer = answerRepository.findById(answer.getId()).orElse(null);

        // then
        Assertions.assertEquals(answer, findAnswer);
    }

    @Test
    public void 답변수정()
    {
        // given
        User user = createUser();

        InquiryWriteRequestDto inquiryWriteRequestDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(inquiryWriteRequestDto);

        AnswerWriteRequestDto answerWriteRequestDto = createAnswer();
        Answer answer = boardService.writeAnswer(inquiry.getId(), answerWriteRequestDto);

        AnswerUpdateRequestDto answerUpdateRequestDto = new AnswerUpdateRequestDto("용내");

        // when
        boardService.updateAnswer(answer.getId(), answerUpdateRequestDto);

        // then
        Assertions.assertEquals("용내", answer.getContent());
    }

    @Test
    public void 답변이_달린_문의_보기()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto inquiryDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(inquiryDto);

        AnswerWriteRequestDto answerDto = createAnswer();
        Answer answer = boardService.writeAnswer(inquiry.getId(), answerDto);

        // when
        Inquiry findInquiry = boardService.viewInquiry(inquiry.getId(), user.getId());

        // then
        assertEquals(findInquiry.getAnswer(), answer);
    }

    @Test
    public void 공지작성()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto requestDto = createNotice(user);

        // when
        Notice notice = boardService.writeNotice(requestDto);
        Notice findOne = noticeRepository.findById(notice.getId()).orElse(null);

        // then
        Assertions.assertEquals(notice, findOne);
    }

    @Test
    public void 공지수정()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto writeRequestDto = createNotice(user);
        Notice notice = boardService.writeNotice(writeRequestDto);
        NoticeUpdateRequestDto updateRequestDto = new NoticeUpdateRequestDto(user.getId(), "목제", "용내");

        // when
        boolean result = boardService.updateNotice(notice.getId(), updateRequestDto);

        // then
        Assertions.assertEquals(true, result);
        Assertions.assertEquals("목제", notice.getTitle());
        Assertions.assertEquals("용내", notice.getContent());
    }

    @Test
    public void 공지상세조회()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto requestDto = createNotice(user);
        Notice notice = boardService.writeNotice(requestDto);

        // when
        Notice findOne = boardService.viewNotice(notice.getId());

        // then
        Assertions.assertEquals(notice, findOne);
    }

    @Test
    public void 공지목록조회()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto requestDto = createNotice(user);

        for(int i=0; i<20; i++)
            boardService.writeNotice(requestDto);

        // when
        List<NoticeListDto> notices = boardService.listNotice()
                .stream()
                .map(NoticeListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(20, notices.size());
        Assertions.assertEquals(1, notices.get(0).getId());
    }

    @Test
    public void 공지삭제()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto requestDto = createNotice(user);
        Notice notice = boardService.writeNotice(requestDto);

        // when
        boolean result1 = boardService.deleteNotice(notice.getId());
        boolean result2 = boardService.deleteNotice(notice.getId());
        Notice findOne = noticeRepository.findByIdAndDeletedDateIsNull(notice.getId()).orElse(null);

        // then
        Assertions.assertEquals(true, result1);
        Assertions.assertEquals(false, result2);
        Assertions.assertEquals(null, findOne);
    }

    @Test
    public void 삭제된_공지_제외한_목록조회()
    {
        // given
        User user = createUser();
        NoticeWriteRequestDto requestDto = createNotice(user);
        for(int i=0; i<20; i++)
        {
            Notice notice = boardService.writeNotice(requestDto);
            // when
            if(i % 10 == 0)
                boardService.deleteNotice(notice.getId());
        }

        List<NoticeListDto> notices = boardService.listNotice()
                .stream()
                .map(NoticeListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(18, notices.size());
    }

    private User createUser()
    {
        User user = User.builder()
                .id("testman")
                .password("1234")
                .businessNo("1234512345")
                .name("실험")
                .email("testman@naver.com")
                .phone("010-1234-5678")
                .address("서울")
                .build();

        return userRepository.save(user);
    }

    private InquiryWriteRequestDto createInquiry(User user)
    {
        InquiryWriteRequestDto requestDto = new InquiryWriteRequestDto(user.getId(), "제목", "내용");

        return requestDto;
    }

    private AnswerWriteRequestDto createAnswer()
    {
        AnswerWriteRequestDto requestDto = new AnswerWriteRequestDto("답변");

        return requestDto;
    }

    private NoticeWriteRequestDto createNotice(User user)
    {
        NoticeWriteRequestDto requestDto = new NoticeWriteRequestDto(user.getId(), "제목", "내용");

        return requestDto;
    }
}
