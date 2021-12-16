package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.InquiryDto;
import kr.co.easystock.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    @GetMapping("/inquiry/write")
    public String inquiry()
    {
        return "inquiryForm";
    }

    @PostMapping("/inquiry/write")
    @ResponseBody
    public int inquirySave(InquiryDto.InquiryFormDto inquiryFormDTO)
    {
        return boardService.inquirySave(inquiryFormDTO);
    }

    @GetMapping("/inquiry/{id}")
    public String getInquiry(@PathVariable int id, Model model)
    {
        model.addAttribute("inquiry", boardService.getInquiry(id));
        return "inquiry";
        //return boardService.getInquiry(id);
    }

    @GetMapping("/inquiry")
    public String getInquiryList(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable, Model model)
    {
        model.addAttribute("inquiryList", boardService.getInquiryList(pageable));
        return "inquiryList";
    }
}
