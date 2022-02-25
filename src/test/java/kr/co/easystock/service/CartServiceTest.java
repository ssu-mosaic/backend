package kr.co.easystock.service;

import kr.co.easystock.controller.dto.CartItemDto;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.cart.CartItem;
import kr.co.easystock.domain.cart.CartItemRepository;
import kr.co.easystock.domain.cart.CartRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.CartItemDto.*;
import static org.junit.Assert.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 7:14
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CartServiceTest
{
    @Autowired
    private CartService cartService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 장바구니_물품추가()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item = createItem(retailer);
        CartItemAddRequestDto requestDto = createCartItem(user, item);

        // when
        CartItem cartItem = cartService.add(requestDto);
        CartItem findOne = cartItemRepository.findById(cartItem.getId()).orElse(null);

        // then
        Assertions.assertEquals(cartItem, findOne);

    }

    @Test
    public void 장바구니_물품수정()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item = createItem(retailer);
        CartItemAddRequestDto addRequestDto = createCartItem(user, item);

        CartItem cartItem = cartService.add(addRequestDto);

        // when
        CartItemUpdateRequestDto updateRequestDto = new CartItemUpdateRequestDto(5);
        boolean result = cartService.update(cartItem.getId(), updateRequestDto);

        // then
        Assertions.assertEquals(true, result);
        Assertions.assertEquals(5, cartItem.getCount());
        Assertions.assertEquals(5*cartItem.getItem().getPrice(), cartItem.getTotalPrice());
    }

    @Test
    public void 장바구니_물품삭제()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item = createItem(retailer);
        CartItemAddRequestDto addRequestDto = createCartItem(user, item);

        CartItem cartItem = cartService.add(addRequestDto);

        // when
        boolean result1 = cartService.delete(cartItem.getId());
        boolean result2 = cartService.delete(cartItem.getId());
        CartItem findOne = cartItemRepository.findById(cartItem.getId()).orElse(null);

        // then
        Assertions.assertEquals(true, result1);
        Assertions.assertEquals(false, result2);
        Assertions.assertEquals(null, findOne);
    }

    @Test
    @Transactional(readOnly = true)
    public void 장바구니_물품목록조회()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item = createItem(retailer);
        CartItemAddRequestDto addRequestDto = createCartItem(user, item);

        for(int i=0; i<20; i++)
            cartService.add(addRequestDto);

        // when
        List<CartItemListDto> list = cartService.list(user.getId())
                .stream()
                .map(CartItemListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(1, list.size());
    }

    private User createUser()
    {
        User user = User.builder()
                .id("testman")
                .password("1234")
                .businessNo("1234512345")
                .name("실험")
                .email("testman@naver.com")
                .phone("010-1234-5678")
                .address("서울")
                .build();

        user.assignCart(new Cart());

        em.persist(user);
        return user;
    }

    private Retailer createRetailer(User user)
    {
        Retailer retailer = new Retailer(user,
                "거래처",
                "testman@naver.com",
                "010-9300-3523",
                "거래처입니다.");

        em.persist(retailer);
        return retailer;
    }

    private Item createItem(Retailer retailer)
    {
        Item item = new Item(retailer,
                "물품",
                10000,
                "개",
                "물품입니다.",
                "카테고리"
                );

        em.persist(item);
        return item;
    }

    private CartItemAddRequestDto createCartItem(User user, Item item)
    {
        return new CartItemAddRequestDto(user.getId(), item.getId(), 10);
    }
}
