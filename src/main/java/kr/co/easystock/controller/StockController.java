package kr.co.easystock.controller;

import kr.co.easystock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.StockDto.*;

@RestController
@RequiredArgsConstructor
public class StockController
{
    private final StockService stockService;

    /**
     * 재고 등록
     * @param requestDto
     * @return Long
     */
    @PostMapping("/stock/add")
    public Long add(@RequestBody StockAddRequestDto requestDto)
    {
        return stockService.add(requestDto).getId();
    }

    /**
     * 재고 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/stock/{id}")
    public boolean update(@PathVariable(name = "id") Long id, @RequestBody StockUpdateRequestDto requestDto)
    {
        return stockService.update(id, requestDto);
    }

    /**
     * 재고 삭제
     * @param id
     * @return boolean
     */
    @DeleteMapping("/stock/{id}")
    public boolean delete(@PathVariable(name = "id") Long id)
    {
        return stockService.delete(id);
    }

    /**
     * 재고 목록 조회
     * @param param
     * @return List
     */
    @PostMapping("/stock")
    public List<StockListDto> list(@RequestBody Map<String, String> param)
    {
        return stockService.list(param.get("userId"))
                .stream()
                .map(StockListDto::new)
                .collect(Collectors.toList());
    }
}
