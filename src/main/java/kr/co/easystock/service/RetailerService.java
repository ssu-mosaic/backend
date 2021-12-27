package kr.co.easystock.service;

import kr.co.easystock.controller.dto.RetailerDto;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import kr.co.easystock.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RetailerService
{
    private final RetailerRepository retailerRepository;

    public boolean add(RetailerDto.RetailerAddRequestDto retailerAddRequestDto)
    {
        Retailer retailer = retailerRepository.save(retailerAddRequestDto.toEntity());
        if(retailer == null)
            return false;

        return true;
    }

    @Transactional
    public boolean update(RetailerDto.RetailerUpdateRequestDto retailerUpdateRequestDto)
    {
        Retailer retailer = retailerRepository.getById(retailerUpdateRequestDto.getRetailerId());
        if(retailer == null)
            return false;

        retailer.update(retailerUpdateRequestDto.getRetailerName(),
                retailerUpdateRequestDto.getRetailerEmail(),
                retailerUpdateRequestDto.getRetailerPhone(),
                retailerUpdateRequestDto.getRetailerAddress(),
                retailerUpdateRequestDto.getRetailerMemo());

        return true;
    }

    public boolean delete(RetailerDto.RetailerDeleteRequestDto retailerDeleteRequestDto)
    {
        Retailer retailer = retailerRepository.getById(retailerDeleteRequestDto.getRetailerId());
        if(retailer == null)
            return false;

        retailerRepository.delete(retailer);
        return true;
    }

    public Retailer getRetailer(int id)
    {
        Retailer retailer = retailerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("거래처가 존재하지 않습니다."));

        return retailer;
    }

    public List<RetailerDto.RetailerListResponseDto> getRetailerList(User user)
    {
        return retailerRepository.findAllByUser(user)
                .stream()
                .map(RetailerDto.RetailerListResponseDto::new)
                .collect(Collectors.toList());
    }
}
