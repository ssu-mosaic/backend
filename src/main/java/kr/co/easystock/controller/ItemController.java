package kr.co.easystock.controller;

import kr.co.easystock.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.ItemDto.*;

@RestController
@RequiredArgsConstructor
public class ItemController
{
    private final ItemService itemService;

    /**
     * 상품 추가
     * @param requestDto
     * @return id
     */
    @PostMapping("/retailer/product/add")
    public Long add(@RequestBody ItemAddRequestDto requestDto)
    {
        return itemService.add(requestDto).getId();
    }

    /**
     * 상품 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/retailer/product/edit/{id}")
    public boolean update(@PathVariable(name = "id") Long id, @RequestBody ItemUpdateRequestDto requestDto)
    {
        return itemService.update(id, requestDto);
    }

    /**
     * 상품 삭제
     * @param id
     * @param param
     * @return boolean
     */
    @PutMapping("/retailer/product/{id}")
    public boolean delete(@PathVariable(name = "id") Long id, @RequestBody Map<String, Long> param)
    {
        return itemService.delete(id, param.get("retailerId"));
    }

    /**
     * 상품 상세 조회(사용안함)
     * @param id
     * @return ItemViewDto
     */
    @PostMapping("/retailer/product/{id}")
    public ItemViewDto view(@PathVariable(name = "id") Long id)
    {
        return new ItemViewDto(itemService.view(id));
    }

    /**
     * 상품 목록 조회
     * @param requestDto
     * @return List
     */
    @PostMapping("/retailer/product")
    public List<ItemListDto> list(@RequestBody ItemListRequestDto requestDto)
    {
        return itemService.list(requestDto.getUserId(), requestDto.getRetailerId())
                .stream()
                .map(ItemListDto::new)
                .collect(Collectors.toList());
    }
}
