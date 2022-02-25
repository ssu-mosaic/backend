package kr.co.easystock.service;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.RetailerDto.*;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-14
 * Time: 오후 5:00
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RetailerServiceTest
{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RetailerService retailerService;
    @Autowired
    private RetailerRepository retailerRepository;
    @Autowired
    private EntityManager em;

    @Test
    public void 거래처추가()
    {
        // given
        User user = createUser();
        RetailerAddRequestDto requestDto = createRetailer(user);

        // when
        Retailer retailer = retailerService.add(requestDto);
        Retailer findOne = retailerRepository.findById(retailer.getId()).orElse(null);

        // then
        Assertions.assertEquals(retailer, findOne);
    }

    @Test
    public void 거래처수정()
    {
        // given
        User user = createUser();
        RetailerAddRequestDto requestDto = createRetailer(user);
        Retailer retailer = retailerRepository.save(requestDto.toEntity(user));

        RetailerUpdateRequestDto updateRequestDto = new RetailerUpdateRequestDto(user.getId(),
                retailer.getId(),
                "처래거",
                "010-8765-4321",
                "testman@gmail.com",
                "거래처였습니다.");

        // when
        retailerService.update(retailer.getId(), updateRequestDto);

        // then
        Assertions.assertEquals(retailer.getName(), updateRequestDto.getRetailerName());
        Assertions.assertEquals(retailer.getEmail(), updateRequestDto.getRetailerEmail());
        Assertions.assertEquals(retailer.getPhone(), updateRequestDto.getRetailerPhoneNo());
        Assertions.assertEquals(retailer.getDetail(), updateRequestDto.getRetailerDetail());
    }

    @Test
    public void 거래처삭제()
    {
        // given
        User user = createUser();
        RetailerAddRequestDto requestDto = createRetailer(user);
        Retailer retailer = retailerRepository.save(requestDto.toEntity(user));

        // when
        retailerService.delete(retailer.getId(), user.getId());
        Retailer findOne = retailerRepository.findById(retailer.getId()).orElse(null);

        // then
        Assertions.assertEquals(findOne, null);
    }

    @Test
    @Transactional(readOnly = true)
    public void 거래처상세보기()
    {
        // given
        User user = createUser();
        RetailerAddRequestDto requestDto = createRetailer(user);
        Retailer retailer = retailerRepository.save(requestDto.toEntity(user));

        // when
        Retailer findOne = retailerService.view(retailer.getId(), user.getId());

        // then
        Assertions.assertEquals(retailer, findOne);
    }

    @Test
    @Transactional(readOnly = true)
    public void 거래처목록보기()
    {
        // given
        User user = createUser();
        RetailerAddRequestDto requestDto1 = createRetailer(user);
        RetailerAddRequestDto requestDto2 = createRetailer(user);
        retailerService.add(requestDto1);
        retailerService.add(requestDto2);

        // when
        List<RetailerListDto> retailerList = retailerService.list(user.getId())
                .stream()
                .map(RetailerListDto::new)
                .collect(Collectors.toList());

        // then
        Assertions.assertEquals(retailerList.size(), 2);
    }

    private User createUser()
    {
        User user = User.builder()
                .id("testman")
                .password("1234")
                .businessNo("1234512345")
                .name("실험")
                .email("testman@naver.com")
                .phone("010-1234-5678")
                .address("서울")
                .build();

        return userRepository.save(user);
    }

    private RetailerAddRequestDto createRetailer(User user)
    {
        return new RetailerAddRequestDto(user.getId(),
                "거래처",
                "010-9300-3523",
                "testman@naver.com",
                "거래처입니다.");
    }
}
