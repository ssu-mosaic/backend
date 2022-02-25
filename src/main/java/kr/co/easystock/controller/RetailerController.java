package kr.co.easystock.controller;

import kr.co.easystock.service.RetailerService;
import kr.co.easystock.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.RetailerDto.*;

@RestController
@RequiredArgsConstructor
public class RetailerController
{
    private final UserService userService;
    private final RetailerService retailerService;

    /**
     * 거래처 추가
     * @param requestDto
     * @return Long
     */
    @PostMapping("/retailer/add")
    public Long add(@RequestBody RetailerAddRequestDto requestDto)
    {
        return retailerService.add(requestDto).getId();
    }

    /**
     * 거래처 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    @PutMapping("/retailer/edit/{id}")
    public boolean update(@PathVariable(name = "id") Long id, @RequestBody RetailerUpdateRequestDto requestDto)
    {
        return retailerService.update(id, requestDto);
    }

    /**
     * 거래처 삭제
     * @param id
     * @param param
     * @return boolean
     */
    @PutMapping("/retailer/{id}")
    public boolean delete(@PathVariable(name = "id") Long id, @RequestBody Map<String, String> param)
    {
        return retailerService.delete(id, param.get("userId"));
    }

    /**
     * 거래처 상세 조회
     * @param id
     * @return RetailerViewDto
     */
    @PostMapping("/retailer/{id}")
    public RetailerViewDto view(@PathVariable Long id, @RequestBody Map<String, String> param)
    {
        return new RetailerViewDto(retailerService.view(id, param.get("userId")));
    }

    /**
     * 거래처 목록 조회
     * @param param
     * @return List
     */
    @PostMapping("/retailer")
    public List<RetailerListDto> list(@RequestBody Map<String, String> param)
    {
        return retailerService.list(param.get("userId"))
                .stream()
                .map(RetailerListDto::new)
                .collect(Collectors.toList());
    }
}
