package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.CustomerDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.CustomerService;
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
public class CustomerController
{
    private final CustomerService customerService;

    @GetMapping("customer")
    public String getCustomerList(@PageableDefault(direction = Sort.Direction.DESC, sort="id") Pageable pageable, Model model, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user == null)
        {
            System.out.println("로그인이 필요합니다.");
            return "redirect:/login";
        }

        model.addAttribute("customerList", customerService.getCustomerList(user, pageable));
        return "customer";
    }

    @GetMapping("customer/add")
    public String customerAddForm()
    {
        return "customerAddForm";
    }

    @PostMapping("customer/add")
    public String customerAdd(CustomerDto.CustomerFormDto customerFormDto, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        if(user == null)
        {
            System.out.println("로그인이 필요합니다.");
            return "redirect:/login";
        }

        customerFormDto.setUser(user);
        int id = customerService.customerAdd(customerFormDto);
        return "redirect:/customer";
    }
}
