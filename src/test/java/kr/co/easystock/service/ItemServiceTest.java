package kr.co.easystock.service;

import kr.co.easystock.controller.dto.ItemDto;
import kr.co.easystock.controller.dto.RetailerDto;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;

import static kr.co.easystock.controller.dto.ItemDto.*;
import static kr.co.easystock.controller.dto.RetailerDto.*;
import static org.junit.Assert.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-14
 * Time: 오후 7:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest
{
    @Autowired
    private ItemService itemService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RetailerRepository retailerRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 상품추가()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        ItemAddRequestDto requestDto = createItem(retailer);

        // when
        Item item = itemService.add(requestDto);
        Item findOne = itemRepository.findById(item.getId()).orElse(null);
        Stock stock = stockRepository.findById(item.getStock().getId()).orElse(null);

        // then
        Assertions.assertEquals(item, findOne);
        Assertions.assertEquals(item.getName(), stock.getName());
    }

    @Test
    public void 상품수정()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        ItemAddRequestDto addRequestDto = createItem(retailer);
        Item item = itemService.add(addRequestDto);

        ItemUpdateRequestDto updateRequestDto = new ItemUpdateRequestDto(retailer.getId(),
                "품상",
                50000,
                "상품이었습니다.",
                "리고테카",
                "포기");

        // when
        itemService.update(item.getId(), updateRequestDto);
        itemRepository.findById(item.getId());

        // then
        Assertions.assertEquals(item.getName(), updateRequestDto.getName());
        Assertions.assertEquals(item.getPrice(), updateRequestDto.getPrice());
        Assertions.assertEquals(item.getDesc(), updateRequestDto.getDesc());
        Assertions.assertEquals(item.getCategory(), updateRequestDto.getCategory());
        Assertions.assertEquals(item.getUnit(), updateRequestDto.getUnit());
    }

    @Test
    public void 상품삭제()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        ItemAddRequestDto requestDto= createItem(retailer);
        Item item = itemService.add(requestDto);

        // when
        itemService.delete(item.getId());
        Item findOne = itemRepository.findById(item.getId()).orElse(null);

        // then
        Assertions.assertEquals(findOne, null);
    }

    @Test
    @Transactional(readOnly = true)
    public void 상품상세조회()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        ItemAddRequestDto requestDto = createItem(retailer);
        Item item = itemService.add(requestDto);

        // when
        Item view = itemService.view(item.getId());

        // then
        Assertions.assertEquals(item, view);
    }

    @Test
    @Transactional(readOnly = true)
    public void 상품목록조회()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        ItemAddRequestDto requestDto1 = createItem(retailer);
        itemService.add(requestDto1);
        ItemAddRequestDto requestDto2 = createItem(retailer);
        itemService.add(requestDto2);
        ItemAddRequestDto requestDto3 = createItem(retailer);
        itemService.add(requestDto3);

        // when
        List<Item> list = itemService.list(retailer.getId());

        // then
        Assertions.assertEquals(3, list.size());
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

        return userRepository.save(user);
    }

    private Retailer createRetailer(User user)
    {
        Retailer retailer = new Retailer(user,
                "거래처",
                "testman@naver.com",
                "서울",
                "010-9300-3523",
                "거래처입니다.");

        return retailerRepository.save(retailer);
    }

    private ItemAddRequestDto createItem(Retailer retailer)
    {
        return new ItemAddRequestDto(retailer.getId(),
                "상품",
                10000,
                "상품입니다.",
                "카테고리",
                "개");
    }
}
