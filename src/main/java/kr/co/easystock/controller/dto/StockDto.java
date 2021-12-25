package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class StockDto
{
    @Getter
    @Setter
    public static class StockFormDto
    {
        private User user;
        private String name;
        private int count;

        public StockFormDto(String name, int count)
        {
            this.name = name;
            this.count = count;
        }

        public Stock toEntity()
        {
            return Stock.builder()
                    .user(user)
                    .name(name)
                    .count(count)
                    .build();
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
