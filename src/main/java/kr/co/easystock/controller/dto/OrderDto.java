package kr.co.easystock.controller.dto;

import lombok.Getter;
import lombok.Setter;

public class OrderDto
{
    @Getter
    @Setter
    public static class OrderRequestDto
    {
        private int itemId;
        private int count;

        public OrderRequestDto(int itemId, int count)
        {
            this.itemId = itemId;
            this.count = count;
        }
    }
}
