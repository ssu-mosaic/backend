package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.user.User;
import lombok.Getter;

public class StockDto
{
    @Getter
    public static class StockAddRequestDto
    {
        private String userId;
        private String stockName;
        private String stockUnit;
        private int stockCnt;

        public StockAddRequestDto(String userId, String stockName, String stockUnit, int stockCnt)
        {
            this.userId = userId;
            this.stockName = stockName;
            this.stockUnit = stockUnit;
            this.stockCnt = stockCnt;
        }

        public Stock toEntity(User user)
        {
            return Stock.builder()
                    .user(user)
                    .name(stockName)
                    .unit(stockUnit)
                    .count(stockCnt)
                    .build();
        }
    }

    @Getter
    public static class StockUpdateRequestDto
    {
        private String userId;
        private Long stockId;
        private String stockName;
        private String stockUnit;
        private int stockCnt;

        public StockUpdateRequestDto(String userId, Long stockId, String stockName, String stockUnit, int stockCnt)
        {
            this.userId = userId;
            this.stockId = stockId;
            this.stockName = stockName;
            this.stockUnit = stockUnit;
            this.stockCnt = stockCnt;
        }

        public Stock toEntity()
        {
            return Stock.builder()
                    .name(stockName)
                    .unit(stockUnit)
                    .count(stockCnt)
                    .build();
        }
    }

    @Getter
    public static class StockListDto
    {
        private Long stockId;
        private String stockName;
        private String stockUnit;
        private int stockCnt;

        public StockListDto(Stock entity)
        {
            this.stockId = entity.getId();
            this.stockName = entity.getName();
            this.stockUnit = entity.getUnit();
            this.stockCnt = entity.getCount();
        }
    }
}
