package kr.co.easystock.service;

import kr.co.easystock.controller.dto.*;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.order.Order;
import kr.co.easystock.domain.order.OrderItem;
import kr.co.easystock.domain.order.OrderItemRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static kr.co.easystock.controller.dto.CartItemDto.*;
import static kr.co.easystock.controller.dto.ItemDto.*;
import static kr.co.easystock.controller.dto.NoticeDto.*;
import static kr.co.easystock.controller.dto.OrderDto.*;
import static kr.co.easystock.controller.dto.StatsDto.*;
import static org.junit.Assert.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-28
 * Time: 오후 6:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class HomeServiceTest
{
    @Autowired
    private HomeService homeService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private BoardService boardService;
    @Autowired
    private EntityManager em;

    @Test
    public void 메인화면_통계()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item = createItem(retailer);

        for(int i=0; i<5; i++)
        {
            // 장바구니 상품 추가
            addCartItem(user, item);

            // 주문 추가
            OrderAddRequestDto requestDto = createOrder(user);
            Order order = orderService.add(requestDto);
            if(i % 2 == 1)
                order.getOrderItems().get(0).complete();
            else
                order.getOrderItems().get(0).cancel();

            // 공지 작성
            NoticeWriteRequestDto notice = createNotice(user);
            boardService.writeNotice(notice);
        }

        // when
        StatsDto stats = homeService.stats(user.getId());
        OrderSpendingStatsDto spendings = stats.getSpendings();

        List<Date> labels = spendings.getLabels();
        List<Integer> datasets = spendings.getDatasets();
        List<Long> orderCompletes = stats.getOrderCompletes();
        List<StockStatsDto> stocks = stats.getStocks();
        List<NoticeStatsDto> notices = stats.getNotices();

        // then
        Assertions.assertEquals(250000, datasets.get(0)); // 비용 합
        Assertions.assertEquals(0, orderCompletes.get(0));
        Assertions.assertEquals(2, orderCompletes.get(1));
        Assertions.assertEquals(3, orderCompletes.get(2));
        Assertions.assertEquals(1, stocks.size()); // 재고
        Assertions.assertEquals(3, notices.size()); // 공지
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
        Item item = itemService.add(new ItemAddRequestDto(retailer.getId(), "물품", 10000, "개", "물품입니다."));

        return item;
    }

    private void addCartItem(User user, Item item)
    {
        cartService.add(new CartItemAddRequestDto(user.getId(), item.getId(), 5));
    }

    private OrderAddRequestDto createOrder(User user)
    {
        OrderAddRequestDto requestDto = new OrderAddRequestDto(user.getId());
        return requestDto;
    }

    private NoticeWriteRequestDto createNotice(User user)
    {
        NoticeWriteRequestDto requestDto = new NoticeWriteRequestDto(user.getId(), "제목", "내용");

        return requestDto;
    }
}
