package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

public class UserDto
{
    @Getter
    public static class UserRegisterRequestDto
    {
        private String userId;
        private String userPwd;
        private String userName;
        private String userBusinessNo;
        private String userEmail;
        private String userAddress;
        private String userPhoneNo;

        @Builder
        public UserRegisterRequestDto(String userId, String userName, String userPwd, String userBusinessNo, String userEmail, String userAddress, String userPhoneNo)
        {
            this.userId = userId;
            this.userName = userName;
            this.userPwd = userPwd;
            this.userBusinessNo = userBusinessNo;
            this.userEmail = userEmail;
            this.userAddress = userAddress;
            this.userPhoneNo = userPhoneNo;
        }

        public User toEntity() {
            return User.builder()
                    .id(userId)
                    .name(userName)
                    .password(userPwd)
                    .businessNo(userBusinessNo)
                    .email(userEmail)
                    .address(userAddress)
                    .phone(userPhoneNo)
                    .build();
        }
    }

    @Getter
    public static class UserInfoDto
    {
        private String userId;
        private String userPwd;
        private String userBusinessNo;
        private String userName;
        private String userEmail;
        private String userAddress;
        private String userPhoneNo;

        public UserInfoDto(User user)
        {
            this.userId = user.getId();
            this.userPwd = user.getPassword();
            this.userBusinessNo = user.getBusinessNo();
            this.userName = user.getName();
            this.userEmail = user.getEmail();
            this.userAddress = user.getAddress();
            this.userPhoneNo = user.getPhone();
        }
    }

    @Getter
    public static class UserUpdateRequestDto
    {
        private String userId;
        private String userPwd;
        private String userBusinessNo;
        private String userName;
        private String userEmail;
        private String userAddress;
        private String userPhoneNo;

        @Builder
        public UserUpdateRequestDto(String userId, String userName, String userPwd, String userBusinessNo, String userEmail, String userAddress, String userPhoneNo)
        {
            this.userId = userId;
            this.userName = userName;
            this.userPwd = userPwd;
            this.userBusinessNo = userBusinessNo;
            this.userEmail = userEmail;
            this.userAddress = userAddress;
            this.userPhoneNo = userPhoneNo;
        }

        public User toEntity()
        {
            return User.builder()
                    .id(userId)
                    .name(userName)
                    .password(userPwd)
                    .businessNo(userBusinessNo)
                    .email(userEmail)
                    .address(userAddress)
                    .phone(userPhoneNo)
                    .build();
        }
    }
}
