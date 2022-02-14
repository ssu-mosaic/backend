package kr.co.easystock.service;

import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
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
        // 양방향 연관관계의 편의 메서드 호출
        retailer.addItem(item);

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
        Item item = itemRepository.findById(id).orElse(null);
        if(item == null)
            return false;

        item.update(requestDto.toEntity());
        return true;
    }

    /**
     * 상품 삭제
     * @param id
     * @return boolean
     */
    public boolean delete(Long id)
    {
        Item item = itemRepository.findById(id).orElse(null);
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
    public List<Item> list(Long retailerId)
    {
        Retailer retailer = retailerRepository.findById(retailerId).orElse(null);
        /*
        retailer가 없으면?
         */

        return itemRepository.findAllByRetailer(retailer);
    }
}
