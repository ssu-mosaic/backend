package kr.co.easystock.service;

import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.retailer.RetailerRepository;
import kr.co.easystock.domain.user.User;
import kr.co.easystock.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static kr.co.easystock.controller.dto.RetailerDto.*;

@RequiredArgsConstructor
@Transactional
@Service
public class RetailerService
{
    private final UserRepository userRepository;
    private final RetailerRepository retailerRepository;

    /**
     * 거래처 추가
     * @param requestDto
     * @return Retailer
     */
    public Retailer add(RetailerAddRequestDto requestDto)
    {
        User user = userRepository.findById(requestDto.getUserId()).orElse(null);
        /*
        user가 없으면?
         */

        return retailerRepository.save(requestDto.toEntity(user));
    }

    /**
     * 거래처 수정
     * @param id
     * @param requestDto
     * @return boolean
     */
    public boolean update(Long id, RetailerUpdateRequestDto requestDto)
    {
        Retailer retailer = retailerRepository.findById(id).orElse(null);
        if(retailer == null)
            return false;

        retailer.update(requestDto.toEntity());
        return true;
    }

    /**
     * 거래처 삭제
     * @param id
     * @return boolean
     */
    public boolean delete(Long id, String userId)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(userId).orElse(null);
        if(user == null)
            return false;

        Retailer retailer = retailerRepository.findByIdAndUser(id, user).orElse(null);
        if(retailer == null)
            return false;

        retailerRepository.delete(retailer);
        return true;
    }

    /**
     * 거래처 상세 조회
     * @param id
     * @return Retailer
     */
    @Transactional(readOnly = true)
    public Retailer view(Long id, String userId)
    {
        User user = userRepository.findByIdAndDeletedDateIsNull(userId).orElse(null);
        if(user == null)
            return null;

        return retailerRepository.findByIdAndUser(id, user).orElse(null);
    }

    /**
     * 거래처 목록 조회
     * @param userId
     * @return List
     */
    @Transactional(readOnly = true)
    public List<Retailer> list(String userId)
    {
        User user = userRepository.findById(userId).orElse(null);
        /*
        user가 없으면?
         */

        return retailerRepository.findAllByUser(user);
    }
}
