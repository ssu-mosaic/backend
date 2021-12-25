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

    public int stockAdd(StockDto.StockFormDto stockFormDto)
    {
        return stockRepository.save(stockFormDto.toEntity()).getId();
    }

    public List<StockDto.StockListResponseDto> getStockList(User user, Pageable pageable)
    {
        return stockRepository.findAllByUser(user, pageable)
                .stream()
                .map(StockDto.StockListResponseDto::new)
                .collect(Collectors.toList());
    }
}
