package kr.co.easystock.service;

import kr.co.easystock.controller.dto.StockDto;
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

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.StockDto.*;
import static kr.co.easystock.controller.dto.StockDto.StockAddRequestDto;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 1:17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StockServiceTest
{
    @Autowired
    private StockService stockService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockRepository stockRepository;

    @Test
    public void 재고추가()
    {
        // given
        User user = createUser();
        StockAddRequestDto requestDto = createStock(user);

        // when
        Stock stock = stockService.add(requestDto);
        Stock findOne = stockRepository.findById(stock.getId()).orElse(null);

        // then
        Assertions.assertEquals(stock, findOne);
    }

    @Test
    public void 재고수정()
    {
        // given
        User user = createUser();
        StockAddRequestDto addRequestDto = createStock(user);
        Stock stock = stockService.add(addRequestDto);

        StockUpdateRequestDto updateRequestDto = new StockUpdateRequestDto(stock.getId(), "고재", "위단", 500);

        // when
        stockService.update(stock.getId(), updateRequestDto);

        // then
        Assertions.assertEquals("고재", stock.getName());
        Assertions.assertEquals(500, stock.getCount());
    }

    @Test
    public void 재고삭제()
    {
        // given
        User user = createUser();
        StockAddRequestDto requestDto = createStock(user);
        Stock stock = stockService.add(requestDto);

        // when
        stockService.delete(stock.getId(), user.getId());
        Stock findOne = stockRepository.findById(stock.getId()).orElse(null);

        // then
        Assertions.assertEquals(null, findOne);
    }

    @Test
    @Transactional(readOnly = true)
    public void 재고목록()
    {
        // given
        User user = createUser();
        StockAddRequestDto requestDto = createStock(user);
        for(int i=0; i<20; i++)
            stockService.add(requestDto);

        // when
        List<StockListDto> list = stockService.list(user.getId())
                .stream()
                .map(StockListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(20, list.size());
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

    private StockAddRequestDto createStock(User user)
    {
        return new StockAddRequestDto(user.getId(), "재고", "단위", 100);
    }
}
