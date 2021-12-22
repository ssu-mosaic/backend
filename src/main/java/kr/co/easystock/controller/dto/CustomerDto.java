package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.customer.Customer;
import kr.co.easystock.domain.user.User;
import lombok.Getter;
import lombok.Setter;

public class CustomerDto
{
    @Setter
    public static class CustomerFormDto
    {
        private User user;
        private String name;
        private String phone;
        private String category;

        public CustomerFormDto(String name, String phone, String category)
        {
            this.name = name;
            this.phone = phone;
            this.category = category;
        }

        public Customer toEntity()
        {
            return Customer.builder()
                    .user(user)
                    .name(name)
                    .phone(phone)
                    .category(category)
                    .build();
        }
    }

    @Getter
    public static class CustomerListResponseDto
    {
        private int id;
        private String name;
        private String phone;

        public CustomerListResponseDto(Customer entity)
        {
            this.id = entity.getId();
            this.name = entity.getName();
            this.phone = entity.getPhone();
        }
    }
}
