package kr.co.easystock.controller;

import kr.co.easystock.controller.dto.RetailerDto;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.service.RetailerService;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RetailerController
{
    private final UserService userService;
    private final RetailerService retailerService;

    @PostMapping("retailer/add")
    public boolean add(@RequestBody RetailerDto.RetailerAddRequestDto retailerAddRequestDto)
    {
        User user = userService.getUser(retailerAddRequestDto.getUserName());
        if(user == null)
            return false;

        retailerAddRequestDto.setUser(user);
        return retailerService.add(retailerAddRequestDto);
    }

    @PostMapping("retailer/edit")
    public boolean update(@RequestBody RetailerDto.RetailerUpdateRequestDto retailerUpdateRequestDto)
    {
        User user = userService.getUser(retailerUpdateRequestDto.getUserName());
        if(user == null)
            return false;

        retailerUpdateRequestDto.setUser(user);
        return retailerService.update(retailerUpdateRequestDto);
    }

    @PostMapping("retailer/delete")
    public boolean delete(@RequestBody int retailerId)
    {
        return retailerService.delete(retailerId);
    }

    @PostMapping("retailer/list")
    public List<RetailerDto.RetailerListResponseDto> getRetailerList(@RequestBody String userName, @PageableDefault(direction = Sort.Direction.DESC, sort="id")Pageable pageable)
    {
        List<RetailerDto.RetailerListResponseDto> retailerListResponseDtoList = new ArrayList<>();
        User user = userService.getUser(userName);
        if(user == null)
            return retailerListResponseDtoList;

        retailerListResponseDtoList = retailerService.getRetailerList(user, pageable);
        return retailerListResponseDtoList;
    }
}
