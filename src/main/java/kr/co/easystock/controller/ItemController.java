package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.ItemDto;
import kr.co.easystock.service.RetailerService;
import kr.co.easystock.service.ItemService;
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
public class ItemController
{
    private final RetailerService retailerService;
    private final ItemService itemService;

    @GetMapping("/customer/{id}/add")
    public String itemAddForm(@PathVariable int id, Model model)
    {
        model.addAttribute("id", id);
        return "itemAddForm";
    }

    @PostMapping("/customer/{id}/add")
    public String itemAdd(@PathVariable int id, ItemDto.ItemFormDto itemFormDto)
    {
        itemService.itemAdd(id, itemFormDto);
        return "redirect:/customer";
    }

    @GetMapping("/customer/{id}")
    public String getItemList(@PathVariable int id, @PageableDefault(direction = Sort.Direction.DESC, sort = "id") Pageable pageable, Model model)
    {
        model.addAttribute("id", id);
        model.addAttribute("itemList", itemService.getItemList(id, pageable));
        return "item";
    }
}
