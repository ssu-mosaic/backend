package kr.co.easystock.service;

import com.querydsl.core.Tuple;
import kr.co.easystock.controller.dto.StatsDto;
import kr.co.easystock.domain.home.HomeRepository;
import kr.co.easystock.domain.notice.Notice;
import kr.co.easystock.domain.notice.NoticeRepository;
import kr.co.easystock.domain.order.OrderItemRepository;
import kr.co.easystock.domain.order.OrderRepository;
import kr.co.easystock.domain.order.OrderStatus;
import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.StatsDto.*;
import static kr.co.easystock.controller.dto.StatsDto.NoticeStatsDto;
import static kr.co.easystock.controller.dto.StatsDto.StockStatsDto;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-28
 * Time: 오후 2:03
 */

@Service
@Transactional
@RequiredArgsConstructor
public class HomeService
{
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final HomeRepository homeRepository;
    private final OrderItemRepository orderItemRepository;
    private final StockRepository stockRepository;
    private final NoticeRepository noticeRepository;

    /**
     * 메인페이지 통계
     * @param userId
     * @return StatsDto
     */
    @Transactional(readOnly = true)
    public StatsDto stats(String userId)
    {
        // 일별 소비 통계(일주일 단위)
        List<Tuple> tuples = homeRepository.orderDayStats(userId);

        List<Date> dateList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        for (Tuple tuple : tuples)
        {
            dateList.add(tuple.get(0, Date.class));
            integerList.add(tuple.get(1, Integer.class));
        }

        OrderSpendingStatsDto orderSpendingStatsDto = new OrderSpendingStatsDto(dateList, integerList);

        // 주문 상태 통계
        tuples = homeRepository.orderStatusStat(userId);
        Long[] status = new Long[]{0L, 0L, 0L};

        int id = 0;
        for (Tuple tuple : tuples)
        {
            OrderStatus orderStatus = tuple.get(0, OrderStatus.class);
            if(orderStatus == OrderStatus.ONPROGRESS)
                id = 0;
            else if(orderStatus == OrderStatus.COMPLETED)
                id = 1;
            else
                id = 2;

            status[id] = tuple.get(1, Long.class);
        }
        List<Long> orderStatusStats = Arrays.asList(status);

        // 재고 통계
        User user = userRepository.findById(userId).orElse(null);

        List<Stock> stocks = stockRepository.findTop5ByUserOrderByCountDesc(user);
        List<StockStatsDto> stockStatsDtos = stocks.stream()
                .map(StockStatsDto::new)
                .collect(Collectors.toList());

        // 공지사항
        List<Notice> notices = noticeRepository.findTop3ByDeletedDateIsNullOrderByCreatedDateDesc();
        List<NoticeStatsDto> noticeStatsDtos = notices.stream()
                .map(NoticeStatsDto::new)
                .collect(Collectors.toList());

        return new StatsDto(orderSpendingStatsDto, orderStatusStats, stockStatsDtos, noticeStatsDtos);
    }
}
