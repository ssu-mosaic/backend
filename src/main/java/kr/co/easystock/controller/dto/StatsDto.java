package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.notice.Notice;
import kr.co.easystock.domain.stock.Stock;
import lombok.Getter;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-28
 * Time: 오후 2:05
 */

@Getter
public class StatsDto
{
    private OrderSpendingStatsDto spendings;
    private List<Long> orderCompletes;
    private List<StockStatsDto> stocks;
    private List<NoticeStatsDto> notices;

    public StatsDto(OrderSpendingStatsDto spendings, List<Long> orderCompletes, List<StockStatsDto> stocks, List<NoticeStatsDto> notices)
    {
        this.spendings = spendings;
        this.orderCompletes = orderCompletes;
        this.stocks = stocks;
        this.notices = notices;
    }

    @Getter
    public static class OrderSpendingStatsDto
    {
        List<Date> labels;
        List<Integer> datasets;

        public OrderSpendingStatsDto() {}

        public OrderSpendingStatsDto(List<Date> labels, List<Integer> datasets)
        {
            this.labels = labels;
            this.datasets = datasets;
        }
    }

    @Getter
    public static class OrderCompleteStatsDto
    {
        List<Integer> data;

        public OrderCompleteStatsDto() {}

        public OrderCompleteStatsDto(List<Integer> data)
        {
            this.data = data;
        }
    }

    @Getter
    public static class StockStatsDto
    {
        private Long productId;
        private String retailerName;
        private String productName;
        private int productCnt;
        private String productUnit;

        public StockStatsDto(Stock entity)
        {
            this.productId = entity.getId();
            if(entity.getItem() != null && entity.getItem().getRetailer() != null)
                this.retailerName = entity.getItem().getRetailer().getName();
            this.productName = entity.getName();
            this.productCnt = entity.getCount();
            this.productUnit = entity.getUnit();
        }
    }

    @Getter
    public static class NoticeStatsDto
    {
        private Long noticeId;
        private LocalDateTime noticeDate;
        private String noticeTitle;

        public NoticeStatsDto(Notice entity)
        {
            this.noticeId = entity.getId();
            this.noticeDate = entity.getCreatedDate();
            this.noticeTitle = entity.getTitle();
        }
    }

    @Getter
    public static class Result<T>
    {
        private T data;

        public Result(T data)
        {
            this.data = data;
        }
    }
}
