package kr.co.easystock.service;

import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.co.easystock.controller.dto.StockDto.StockAddRequestDto;
import static kr.co.easystock.controller.dto.StockDto.StockUpdateRequestDto;

@Service
@RequiredArgsConstructor
@Transactional
public class StockService
{
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    /**
     * 재고 등록
     * @param requestDto
     * @return Stock
     */
    public Stock add(StockAddRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면?
         */

        return stockRepository.save(requestDto.toEntity(user));
    }

    /**
     * 재고 수정
     * @param requestDto
     * @return boolean
     */
    public boolean update(Long id, StockUpdateRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        if(user == null)
            return false;

        Stock stock = stockRepository.findByIdAndUser(id, user).orElse(null);
        if(stock == null)
            return false;

        stock.update(requestDto.getStockName(), requestDto.getStockUnit(), requestDto.getStockCnt());
        return true;
    }

    /**
     * 재고 삭제
     * @param id
     * @param userId
     * @return boolean
     */
    public boolean delete(Long id, String userId)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(userId).orElse(null);
        if(user == null)
            return false;

        Stock stock = stockRepository.findByIdAndUser(id, user).orElse(null);
        if(stock == null)
            return false;

        stockRepository.delete(stock);
        return true;
    }

    /**
     * 재고 목록 조회
     * @param userId
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Stock> list(String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        /*
        user가 없으면?
         */

        return stockRepository.findAllByUser(user);
    }
}
