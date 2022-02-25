package kr.co.easystock.service;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import kr.co.easystock.domain.stock.Stock;
import kr.co.easystock.domain.stock.StockRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.ItemDto.*;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService
{
    private final UserRepository userRepository;
    private final RetailerRepository retailerRepository;
    private final ItemRepository itemRepository;

    /**
     * 상품 추가
     * @param requestDto
     * @return Item
     */
    public Item add(ItemAddRequestDto requestDto)
    {
        Retailer retailer = retailerRepository.findById(requestDto.getRetailerId()).orElse(null);
        /*
        retailer가 없으면?
         */

        Item item = requestDto.toEntity(retailer);
        // 거래처와 매핑(양방향 연관관계 메서드)
        retailer.addItem(item);
        // 재고 생성
        Stock stock = new Stock(retailer.getUser(), item, item.getName(), item.getUnit(), 0);
        // 재고와 매핑(양방향 연관관계 메서드)
        item.createItemToStock(stock);

        return itemRepository.save(item);
    }

    /**
     * 상품 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    public boolean update(Long id, ItemUpdateRequestDto requestDto)
    {
        Retailer retailer = retailerRepository.findById(requestDto.getRetailerId()).orElse(null);
        if(retailer == null)
            return false;

        Item item = itemRepository.findByIdAndRetailer(id, retailer).orElse(null);
        if(item == null)
            return false;

        item.update(requestDto.toEntity());
        return true;
    }

    /**
     * 상품 삭제
     * @param id
     * @param retailerId
     * @return boolean
     */
    public boolean delete(Long id, Long retailerId)
    {
        Retailer retailer = retailerRepository.findById(retailerId).orElse(null);
        if(retailer == null)
            return false;

        Item item = itemRepository.findByIdAndRetailer(id, retailer).orElse(null);
        if(item == null)
            return false;

        itemRepository.delete(item);
        return true;
    }

    /**
     * 상품 상세 조회
     * @param id
     * @return Item
     */
    @Transactional(readOnly = true)
    public Item view(Long id)
    {
        return itemRepository.findById(id).orElse(null);
    }

    /**
     * 상품 목록 조회
     * @param retailerId
     * @return
     */
    @Transactional(readOnly = true)
    public List<Item> list(String userId, Long retailerId)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(userId).orElse(null);
        /*
        user가 없으면?
         */

        Retailer retailer = retailerRepository.findByIdAndUser(retailerId, user).orElse(null);
        /*
        retailer가 없으면?
         */

        return itemRepository.findAllByRetailer(retailer);
    }
}
