package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.CartItemDto;
import kr.co.easystock.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.CartItemDto.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 4:41
 */

@RestController
@RequiredArgsConstructor
public class CartController
{
    private final CartService cartService;

    /**
     * 장바구니 물품 추가
     * @param requestDto
     * @return id
     */
    @PostMapping("/order/cart/add")
    public Long add(@RequestBody CartItemAddRequestDto requestDto)
    {
        return cartService.add(requestDto).getId();
    }

    /**
     * 장바구니 물품 수정
     * @param id
     * @return boolean
     */
    @PutMapping("/order/cart/{id}")
    public boolean update(@PathVariable(name = "id") Long id, CartItemUpdateRequestDto requestDto)
    {
        return cartService.update(id, requestDto);
    }

    /**
     * 장바구니 물품 삭제
     * @param id
     * @return boolean
     */
    @DeleteMapping("/order/cart/{id}")
    public boolean delete(@PathVariable(name = "id") Long id)
    {
        return cartService.delete(id);
    }

    /**
     * 장바구니 목록 조회
     * @param param
     * @return List
     */
    @PostMapping("/order/cart")
    public List<CartItemListDto> list(@RequestBody Map<String, String> param)
    {
        return cartService.list(param.get("userId"))
                .stream()
                .map(CartItemListDto::new)
                .collect(Collectors.toList());
    }
}
