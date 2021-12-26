package kr.co.easystock.controller.dto;

import lombok.Getter;

public class UserDto
{
    @Getter
    public static class UserLoginDto
    {
        private String name;
        private String password;

        public UserLoginDto(String name, String password)
        {
            this.name = name;
            this.password = password;
        }
    }
}
