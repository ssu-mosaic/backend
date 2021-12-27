package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class StockDto
{
    @Getter
    @Setter
    public static class StockAddRequestDto
    {
        private User user;
        private String userName;
        private String stockName;
        private int stockCount;

        public StockAddRequestDto(String userName, String stockName, int stockCount)
        {
            this.userName = userName;
            this.stockName = stockName;
            this.stockCount = stockCount;
        }

        public Stock toEntity()
        {
            return Stock.builder()
                    .user(user)
                    .name(stockName)
                    .count(stockCount)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class StockUpdateRequestDto
    {
        private User user;
        private String userName;
        private int stockId;
        private String stockName;
        private int stockCount;

        public StockUpdateRequestDto(String userName, int stockId, String stockName, int stockCount)
        {
            this.userName = userName;
            this.stockId = stockId;
            this.stockName = stockName;
            this.stockCount = stockCount;
        }
    }

    @Getter
    public static class StockDeleteRequestDto
    {
        private String userName;
        private int stockId;

        public StockDeleteRequestDto(String userName, int stockId)
        {
            this.userName = userName;
            this.stockId = stockId;
        }
    }

    @Getter
    public static class StockListResponseDto
    {
        private int id;
        private String name;
        private int count;

        public StockListResponseDto(Stock entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.count = entity.getCount();
        }
    }
}
