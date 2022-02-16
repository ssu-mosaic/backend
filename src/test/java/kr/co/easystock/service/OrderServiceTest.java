package kr.co.easystock.service;

import kr.co.easystock.controller.dto.CartItemDto;
import kr.co.easystock.controller.dto.ItemDto;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.cart.Cart;
import kr.co.easystock.domain.cart.CartItemRepository;
import kr.co.easystock.domain.order.*;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.OrderDto.OrderAddRequestDto;
import static kr.co.easystock.controller.dto.OrderDto.OrderListDto;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-16
 * Time: 오후 5:18
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest
{
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 주문하기()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item1 = createItem(retailer);
        Item item2 = createItem(retailer);
        for(int i=0; i<5; i++)
        {
            addCartItem(user, item1);
            addCartItem(user, item2);
        }

        OrderAddRequestDto requestDto = createOrder(user);
        Order order = orderService.add(requestDto);

        // when
        Order findOne = orderRepository.findById(order.getId()).orElse(null);

        // then
        Assertions.assertEquals(2, findOne.getOrderItems().size());
    }

    @Test
    public void 상품주문취소()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item1 = createItem(retailer);
        Item item2 = createItem(retailer);
        for(int i=0; i<5; i++)
        {
            addCartItem(user, item1);
            addCartItem(user, item2);
        }

        OrderAddRequestDto requestDto = createOrder(user);
        Order order = orderService.add(requestDto);
        OrderItem orderItem1 = order.getOrderItems().get(0);

        // when
        orderService.cancel(orderItem1.getId());
        List<Order> list = orderRepository.findAllByUser(user);
        Order findOne = list.get(0);

        // then
        Assertions.assertEquals(OrderStatus.CANCELED, findOne.getOrderItems().get(0).getStatus());
        Assertions.assertEquals(OrderStatus.ONPROGRESS, findOne.getOrderItems().get(1).getStatus());
    }

    @Test
    public void 상품수취완료()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item1 = createItem(retailer);
        Item item2 = createItem(retailer);
        for(int i=0; i<5; i++)
        {
            addCartItem(user, item1);
            addCartItem(user, item2);
        }

        OrderAddRequestDto requestDto = createOrder(user);
        Order order = orderService.add(requestDto);
        OrderItem orderItem1 = order.getOrderItems().get(0);
        OrderItem orderItem2 = order.getOrderItems().get(1);

        // when
        Stock stock1 = stockRepository.findById(orderItem1.getItem().getStock().getId()).orElse(null);
        Stock stock2 = stockRepository.findById(orderItem2.getItem().getStock().getId()).orElse(null);
        System.out.println(stock1.getCount());
        System.out.println(stock2.getCount());

        orderService.complete(orderItem1.getId());
        List<Order> list = orderRepository.findAllByUser(user);
        Order findOne = list.get(0);
        System.out.println(stock1.getCount());
        System.out.println(stock2.getCount());

        // then
        Assertions.assertEquals(OrderStatus.COMPLETED, findOne.getOrderItems().get(0).getStatus());
        Assertions.assertEquals(orderItem1.getCount(), stock1.getCount());
        Assertions.assertEquals(OrderStatus.ONPROGRESS, findOne.getOrderItems().get(1).getStatus());
        Assertions.assertEquals(0, stock2.getCount());
    }

    @Test
    @Transactional(readOnly = true)
    public void 주문상세조회()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item1 = createItem(retailer);
        Item item2 = createItem(retailer);
        for(int i=0; i<5; i++)
        {
            addCartItem(user, item1);
            addCartItem(user, item2);
        }

        OrderAddRequestDto requestDto = createOrder(user);
        Order order = orderService.add(requestDto);

        // when
        Order findOne = orderService.view(order.getId());

        // then
        Assertions.assertEquals(order, findOne);
    }

    @Test
    @Transactional(readOnly = true)
    public void 주문목록조회()
    {
        // given
        User user = createUser();
        Retailer retailer = createRetailer(user);
        Item item1 = createItem(retailer);
        Item item2 = createItem(retailer);
        for(int i=0; i<5; i++)
        {
            addCartItem(user, item1);
            addCartItem(user, item2);
        }

        OrderAddRequestDto requestDto = createOrder(user);
        Order order = orderService.add(requestDto);

        // when
        List<OrderListDto> list = orderService.list(user.getId())
                .stream()
                .map(OrderListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(1, list.size());
        Assertions.assertEquals(2, list.get(0).getOrderProducts().size());
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
                "서울",
                "010-9300-3523",
                "거래처입니다.");

        em.persist(retailer);
        return retailer;
    }

    private Item createItem(Retailer retailer)
    {
        Item item = itemService.add(new ItemDto.ItemAddRequestDto(retailer.getId(), "물품", 10000, "개", "물품입니다.", "카테고리"));

        return item;
    }

    private void addCartItem(User user, Item item)
    {
        cartService.add(new CartItemDto.CartItemAddRequestDto(user.getId(), item.getId(), 5));
    }

    private OrderAddRequestDto createOrder(User user)
    {
        OrderAddRequestDto requestDto = new OrderAddRequestDto(user.getId());
        return requestDto;
    }
}
