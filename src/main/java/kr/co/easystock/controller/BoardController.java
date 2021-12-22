package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.AnswerDto;
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

@Controller
@RequiredArgsConstructor
public class BoardController
{
    private final BoardService boardService;

    @GetMapping("/inquiry/write")
    public String inquiryForm()
    {
        return "inquiryForm";
    }

    @PostMapping("/inquiry/write")
    public String inquirySave(InquiryDto.InquiryFormDto inquiryFormDTO)
    {
        boardService.inquirySave(inquiryFormDTO);
        return "redirect:/inquiry";
    }

    @GetMapping("/inquiry/{id}/answer")
    public String answerForm(@PathVariable int id, Model model)
    {
        model.addAttribute("inquiry", boardService.getInquiry(id));
        return "/inquiryAnswerForm";
    }

    @PostMapping("/inquiry/{id}/answer")
    public String answerSave(@PathVariable int id, AnswerDto.AnswerFormDto answerFormDto)
    {
        boardService.answerSave(id, answerFormDto);
        return "redirect:/inquiry/" + id;
    }

    @GetMapping("/inquiry/{id}")
    public String getInquiry(@PathVariable int id, Model model)
    {
        model.addAttribute("inquiry", boardService.getInquiry(id));
        return "inquiryView";
        //return boardService.getInquiry(id);
    }

    @GetMapping("/inquiry")
    public String getInquiryList(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable, Model model)
    {
        model.addAttribute("inquiryList", boardService.getInquiryList(pageable));
        return "inquiry";
    }
}
