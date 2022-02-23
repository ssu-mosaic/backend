package kr.co.easystock.controller;

import kr.co.easystock.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.AnswerDto.AnswerUpdateRequestDto;
import static kr.co.easystock.controller.dto.AnswerDto.AnswerWriteRequestDto;
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
     * @param param
     * @return InquiryViewDto
     */
    @PostMapping("/qna/{id}")
    public InquiryViewDto viewInquiry(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        return new InquiryViewDto(boardService.viewInquiry(id, userId));
    }

    /**
     * 1:1 문의 목록 조회
     * @param param
     * @return List
     */
    @PostMapping("/qna")
    public List<InquiryListDto> listInquiry(@RequestBody Map<String, String> param)
    {
        return boardService.listInquiry(param.get("userId"))
                .stream()
                .map(InquiryListDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 관리자 1:1 문의 목록 조회
     * @return List
     */
    @GetMapping("/admin/qna")
    public List<InquiryListDto> adminListInquiry()
    {
        return boardService.adminListInquiry()
                .stream()
                .map(InquiryListDto::new)
                .collect(Collectors.toList());
    }

    /**
     * 문의 삭제
     * @param id
     * @param param
     * @return boolean
     */
    @PutMapping("/qna/{id}")
    public boolean deleteInquiry(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> param)
    {
        String userId = param.get("userId");
        return boardService.deleteInquiry(id, userId);
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
     * @return List
     */
    @GetMapping("/notice")
    public List<NoticeListDto> listNotice()
    {
        return boardService.listNotice()
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
