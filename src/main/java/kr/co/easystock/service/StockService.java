package kr.co.easystock.service;

import kr.co.easystock.controller.dto.StockDto;
import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StockService
{
    private final StockRepository stockRepository;

    public boolean add(StockDto.StockAddRequestDto stockAddRequestDto)
    {
        Stock stock = stockRepository.save(stockAddRequestDto.toEntity());
        if(stock == null)
            return false;

        return true;
    }

    public boolean update(StockDto.StockUpdateRequestDto stockUpdateRequestDto)
    {
        Stock stock = stockRepository.getById(stockUpdateRequestDto.getStockId());
        if(stock == null)
            return false;

        stock.update(stockUpdateRequestDto.getStockName(), stockUpdateRequestDto.getStockCount());
        return true;
    }

    public boolean delete(int stockId)
    {
        Stock stock = stockRepository.getById(stockId);
        if(stock == null)
            return false;

        stockRepository.delete(stock);
        return true;
    }

    public List<StockDto.StockListResponseDto> getStockList(User user)
    {
        return stockRepository.findAllByUser(user)
                .stream()
                .map(StockDto.StockListResponseDto::new)
                .collect(Collectors.toList());
    }
}
