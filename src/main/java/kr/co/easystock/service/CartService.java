package kr.co.easystock.service;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.cart.CartItem;
import kr.co.easystock.domain.cart.CartItemRepository;
import kr.co.easystock.domain.cart.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.easystock.controller.dto.CartItemDto.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 4:42
 */

@RequiredArgsConstructor
@Transactional
@Service
public class CartService
{
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;

    /**
     * 장바구니 물품 추가
     * @param requestDto
     * @return CartItem
     */
    public CartItem add(CartItemAddRequestDto requestDto)
    {
        Cart cart = cartRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        cart가 없으면?
         */
        Item item = itemRepository.findById(requestDto.getProductId()).orElse(null);
        /*
        item이 없으면?
         */

        /*
        아이템이 이미 존재하면 수량 증가
                      존재하지 않으면 생성해서 추가
         */
        CartItem cartItem = cartItemRepository.findByCartAndItem(cart, item).orElse(null);
        // 수량 증가
        if(cartItem != null)
            cartItem.update(cartItem.getCount() + requestDto.getProductCnt());
        // 생성해서 추가
        else
        {
            // 아이템 생성
            cartItem = requestDto.toEntity(cart, item);
            // 양방향 연관관계를 위한 메서드 호출
            cart.addCartItem(cartItem);
            cartItem = cartItemRepository.save(cartItem);
        }

        return cartItem;
    }

    /**
     * 장바구니 물품 수정
     * @param id
     * @param count
     * @return boolean
     */
    public boolean update(Long id, Integer count)
    {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        if(cartItem == null)
            return false;

        cartItem.update(count);
        return true;
    }

    /**
     * 장바구니 물품 삭제
     * @param id
     * @return boolean
     */
    public boolean delete(Long id)
    {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        if(cartItem == null)
            return false;

        cartItem.getCart().deleteCartItem(cartItem);
        cartItemRepository.delete(cartItem);
        return true;
    }

    /**
     * 장바구니 목록 조회
     * @param id
     * @return List
     */
    public List<CartItem> list(String id)
    {
        Cart cart = cartRepository.findById(id).orElse(null);
        /*
        cart가 없으면?
         */

        return cartItemRepository.findAllByCart(cart);
    }
}
