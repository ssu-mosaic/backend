package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.StockDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.StockService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class StockController
{
    private final StockService stockService;

    @GetMapping("/stock")
    public String getStockList(@PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user == null)
        {
            System.out.println("로그인이 필요합니다.");
            return "redirect:/login";
        }

        model.addAttribute("stockList", stockService.getStockList(user, pageable));
        return "stock";
    }

    @GetMapping("/stock/add")
    public String stockForm()
    {
        return "stockAddForm";
    }

    @PostMapping("/stock/add")
    public String stockAdd(StockDto.StockFormDto stockFormDto, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user == null)
        {
            System.out.println("로그인이 필요합니다.");
            return "redirect:/login";
        }

        stockFormDto.setUser(user);
        int id = stockService.stockAdd(stockFormDto);
        return "redirect:/stock";
    }
}
