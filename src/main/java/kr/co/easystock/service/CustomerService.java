package kr.co.easystock.service;

import kr.co.easystock.controller.dto.CustomerDto;
import kr.co.easystock.domain.customer.CustomerRepository;
import kr.co.easystock.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerService
{
    private final CustomerRepository customerRepository;

    public int customerAdd(CustomerDto.CustomerFormDto customerFormDto)
    {
        return customerRepository.save(customerFormDto.toEntity()).getId();
    }

    public List<CustomerDto.CustomerListResponseDto> getCustomerList(User user, Pageable pageable)
    {
        return customerRepository.findAllByUser(user, pageable)
                .stream()
                .map(CustomerDto.CustomerListResponseDto::new)
                .collect(Collectors.toList());
    }
}
