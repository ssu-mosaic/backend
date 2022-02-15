package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class StockDto
{
    @Getter
    public static class StockAddRequestDto
    {
        private String userId;
        private String stockName;
        private int stockCount;

        public StockAddRequestDto(String userId, String stockName, int stockCount)
        {
            this.userId = userId;
            this.stockName = stockName;
            this.stockCount = stockCount;
        }

        public Stock toEntity(User user)
        {
            return Stock.builder()
                    .user(user)
                    .name(stockName)
                    .count(stockCount)
                    .build();
        }
    }

    @Getter
    public static class StockUpdateRequestDto
    {
        private Long stockId;
        private String stockName;
        private int stockCount;

        public StockUpdateRequestDto(Long stockId, String stockName, int stockCount)
        {
            this.stockId = stockId;
            this.stockName = stockName;
            this.stockCount = stockCount;
        }

        public Stock toEntity()
        {
            return Stock.builder()
                    .name(stockName)
                    .count(stockCount)
                    .build();
        }
    }

    @Getter
    public static class StockListDto
    {
        private Long stockId;
        private String stockName;
        private int stockCount;

        public StockListDto(Stock entity)
        {
            this.stockId = entity.getId();
            this.stockName = entity.getName();
            this.stockCount = entity.getCount();
        }
    }
}
