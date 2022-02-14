package kr.co.easystock.service;

import kr.co.easystock.controller.dto.AnswerDto;
import kr.co.easystock.controller.dto.InquiryDto;
import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.answer.AnswerRepository;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.inquiry.InquiryRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static kr.co.easystock.controller.dto.AnswerDto.*;
import static kr.co.easystock.controller.dto.InquiryDto.*;
import static org.junit.Assert.*;

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
                .title("목제")
                .content("용내")
                .category("리고테카")
                .build();

        boardService.updateInquiry(inquiry.getId(), requestDto);
        em.flush();

        // then
        Assertions.assertEquals(inquiry.getTitle(), requestDto.getTitle());
        Assertions.assertEquals(inquiry.getContent(), requestDto.getContent());
        Assertions.assertEquals(inquiry.getCategory(), requestDto.getCategory());
    }

    @Test
    @Transactional(readOnly = true)
    public void 문의글보기()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(requestDto);

        // when
        Inquiry findInquiry = boardService.viewInquiry(inquiry.getId());

        // then
        Assertions.assertEquals(inquiry, findInquiry);
    }

    @Test
    @Transactional(readOnly = true)
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
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        List<InquiryListDto> inquiryListDtos = boardService.viewInquiryList(pageable);

        // then
        for(InquiryListDto dto : inquiryListDtos)
            System.out.println(dto.getId() + " : " + dto.getTitle());

        Assertions.assertEquals(inquiryListDtos.size(), 10);
    }

    @Test
    public void 문의삭제()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto requestDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(requestDto);

        // when
        boardService.deleteInquiry(inquiry.getId());

        // then
        Assertions.assertNotEquals(null, inquiry.getDeletedDate());
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
    @Transactional(readOnly = true)
    public void 답변이_달린_문의_보기()
    {
        // given
        User user = createUser();
        InquiryWriteRequestDto inquiryDto = createInquiry(user);
        Inquiry inquiry = boardService.writeInquiry(inquiryDto);

        AnswerWriteRequestDto answerDto = createAnswer();
        Answer answer = boardService.writeAnswer(inquiry.getId(), answerDto);

        // when
        Inquiry findInquiry = boardService.viewInquiry(inquiry.getId());

        // then
        assertEquals(findInquiry.getAnswer(), answer);
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
        InquiryWriteRequestDto requestDto = new InquiryWriteRequestDto(user.getId(), "제목", "내용", "카테고리");

        return requestDto;
    }

    private AnswerWriteRequestDto createAnswer()
    {
        AnswerWriteRequestDto requestDto = new AnswerWriteRequestDto("답변");

        return requestDto;
    }
}
