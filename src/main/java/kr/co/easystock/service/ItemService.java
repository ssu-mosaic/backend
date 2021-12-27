package kr.co.easystock.service;

import kr.co.easystock.controller.dto.ItemDto;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final RetailerRepository retailerRepository;
    private final ItemRepository itemRepository;

    public int itemAdd(int id, ItemDto.ItemFormDto itemFormDto)
    {
        Retailer retailer = retailerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("거래처가 존재하지 않습니다."));
        itemFormDto.setRetailer(retailer);

        Item item = itemRepository.save(itemFormDto.toEntity());
        return item.getId();
    }

    public List<ItemDto.ItemListResponseDto> getItemList(int id, Pageable pageable)
    {
        Retailer retailer = retailerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("거래처가 존재하지 않습니다."));

        return itemRepository.findAllByRetailer(retailer, pageable)
                .stream()
                .map(ItemDto.ItemListResponseDto::new)
                .collect(Collectors.toList());
    }
}
