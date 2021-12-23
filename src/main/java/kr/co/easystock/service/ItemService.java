package kr.co.easystock.service;

import kr.co.easystock.controller.dto.ItemDto;
import kr.co.easystock.domain.Item.Item;
import kr.co.easystock.domain.Item.ItemRepository;
import kr.co.easystock.domain.customer.Customer;
import kr.co.easystock.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService
{
    private final CustomerRepository customerRepository;
    private final ItemRepository itemRepository;

    public int itemAdd(int id, ItemDto.ItemFormDto itemFormDto)
    {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("거래처가 존재하지 않습니다."));
        itemFormDto.setCustomer(customer);

        Item item = itemRepository.save(itemFormDto.toEntity());
        return item.getId();
    }

    public List<ItemDto.ItemListResponseDto> getItemList(int id, Pageable pageable)
    {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("거래처가 존재하지 않습니다."));

        return itemRepository.findAllByCustomer(customer, pageable)
                .stream()
                .map(ItemDto.ItemListResponseDto::new)
                .collect(Collectors.toList());
    }
}
