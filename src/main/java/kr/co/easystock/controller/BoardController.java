package kr.co.easystock.controller;

import kr.co.easystock.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.AnswerDto.*;
import static kr.co.easystock.controller.dto.InquiryDto.*;
import static kr.co.easystock.controller.dto.NoticeDto.*;

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
    public List<InquiryListDto> listInquiry(@PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return boardService.listInquiry(pageable)
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

    /**
     * 공지 작성
     * @param requestDto
     * @return Long
     */
    @PostMapping("/notice")
    public Long writeNotice(NoticeWriteRequestDto requestDto)
    {
        return boardService.writeNotice(requestDto).getId();
    }

    /**
     * 공지 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/notice")
    public boolean updateNotice(@PathVariable(name = "id") Long id, @RequestBody NoticeUpdateRequestDto requestDto)
    {
        return boardService.updateNotice(id, requestDto);
    }

    /**
     * 공지 상세 조회
     * @param id
     * @return NoticeViewDto
     */
    @GetMapping("/notice/{id}")
    public NoticeViewDto viewNotice(@PathVariable(name = "id") Long id)
    {
        return new NoticeViewDto(boardService.viewNotice(id));
    }

    /**
     * 공지 목록 조회
     * @param pageable
     * @return List
     */
    @GetMapping("/notice")
    public List<NoticeListDto> listNotice(@PageableDefault(page = 0, size = 20, sort = "id", direction = Sort.Direction.DESC) Pageable pageable)
    {
        return boardService.listNotice(pageable)
                .stream()
                .map(NoticeListDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 공지 삭제
     * @param id
     * @return boolean
     */
    @DeleteMapping("/admin/notice/{id}")
    public boolean deleteNotice(@PathVariable(name = "id") Long id)
    {
        return boardService.deleteNotice(id);
    }
}
