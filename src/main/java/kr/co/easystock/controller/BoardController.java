package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.InquiryDTO;
import kr.co.easystock.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    @GetMapping("/inquiry")
    public String inquiry()
    {
        return "inquiryForm";
    }

    @PostMapping("/inquiry")
    @ResponseBody
    public int inquirySave(InquiryDTO.InquiryFormDTO inquiryFormDTO)
    {
        return boardService.inquirySave(inquiryFormDTO);
    }
}
