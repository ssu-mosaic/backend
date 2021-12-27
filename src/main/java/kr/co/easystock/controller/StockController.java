package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.StockDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.StockService;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class StockController
{
    private final UserService userService;
    private final StockService stockService;

    @PostMapping("/stock/add")
    public boolean add(@RequestBody StockDto.StockAddRequestDto stockAddRequestDto)
    {
        User user = userService.getUser(stockAddRequestDto.getUserName());
        if(user == null)
            return false;

        stockAddRequestDto.setUser(user);
        return stockService.add(stockAddRequestDto);
    }

    @PostMapping("/stock/edit")
    public boolean update(@RequestBody StockDto.StockUpdateRequestDto stockUpdateRequestDto)
    {
        return stockService.update(stockUpdateRequestDto);
    }

    @PostMapping("/stock/delete")
    public boolean delete(@RequestBody StockDto.StockDeleteRequestDto stockDeleteRequestDto)
    {
        return stockService.delete(stockDeleteRequestDto);
    }

    @PostMapping("stock/list")
    public List<StockDto.StockListResponseDto> getStockList(@RequestBody Map<String, String> param)
    {
        List<StockDto.StockListResponseDto> stockListResponseDtoList = new ArrayList<>();
        User user = userService.getUser(param.get("userName"));
        if(user == null)
            return stockListResponseDtoList;

        stockListResponseDtoList = stockService.getStockList(user);
        return stockListResponseDtoList;
    }
}
