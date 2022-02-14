package kr.co.easystock.controller;

import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.AnswerDto.AnswerUpdateRequestDto;
import static kr.co.easystock.controller.dto.AnswerDto.AnswerWriteRequestDto;
import static kr.co.easystock.controller.dto.InquiryDto.*;

@RestController
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    /**
     * 문의 작성
     * @param requestDto
     * @return Long
     */
    @PostMapping("/qna/write")
    public Long writeInquiry(@RequestBody InquiryWriteRequestDto requestDto)
    {
        return boardService.writeInquiry(requestDto).getId();
    }

    /**
     * 문의 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/qna/edit/{id}")
    public boolean updateInquiry(@PathVariable(name = "id") Long id, @RequestBody InquiryUpdateRequestDto requestDto)
    {
        return boardService.updateInquiry(id, requestDto);
    }

    /**
     * 문의 상세 조회
     * @param id
     * @return InquiryViewDto
     */
    @GetMapping("/qna/{id}")
    public InquiryViewDto viewInquiry(@PathVariable(name = "id") Long id)
    {
        return new InquiryViewDto(boardService.viewInquiry(id));
    }

    /**
     * 문의 목록 조회
     * @param pageable
     * @return List
     */
    @GetMapping("/qna")
    public List<InquiryListDto> viewInquiryList(@PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return boardService.viewInquiryList(pageable)
                .stream()
                .map(InquiryListDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 답변 작성
     * @param id
     * @param requestDto
     * @return Long
     */
    @PostMapping("/qna/{id}/answer")
    public Long writeAnswer(@PathVariable(name = "id") Long id, @RequestBody AnswerWriteRequestDto requestDto)
    {
        return boardService.writeAnswer(id, requestDto).getId();
    }

    /**
     * 답변 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/qna/{id}/answer")
    public boolean updateAnswer(@PathVariable(name = "id") Long id, @RequestBody AnswerUpdateRequestDto requestDto)
    {
        return boardService.updateAnswer(id, requestDto);
    }
}
