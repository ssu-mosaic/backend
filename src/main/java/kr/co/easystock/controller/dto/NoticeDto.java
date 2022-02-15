package kr.co.easystock.controller.dto;

import kr.co.easystock.controller.dto.UserDto.UserInfoDto;
import kr.co.easystock.domain.notice.Notice;
import kr.co.easystock.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-15
 * Time: 오후 1:38
 */

public class NoticeDto
{
    @Getter
    public static class NoticeWriteRequestDto
    {
        private String userId;
        private String title;
        private String content;

        public NoticeWriteRequestDto(String userId, String title, String content)
        {
            this.userId = userId;
            this.title = title;
            this.content = content;
        }

        public Notice toEntity(User user)
        {
            return new Notice(user, title, content);
        }
    }

    @Getter
    public static class NoticeUpdateRequestDto
    {
        private String userId;
        private String title;
        private String content;

        public NoticeUpdateRequestDto(String userId, String title, String content)
        {
            this.userId = userId;
            this.title = title;
            this.content = content;
        }

        public Notice toEntity()
        {
            return new Notice(null, title, content);
        }
    }

    @Getter
    public static class NoticeViewDto
    {
        private Long id;
        private UserInfoDto user;
        private String title;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        public NoticeViewDto(Notice entity)
        {
            this.id = entity.getId();
            this.user = new UserInfoDto(entity.getUser());
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.createdDate = entity.getCreatedDate();
            this.lastModifiedDate = entity.getLastModifiedDate();
        }
    }

    @Getter
    public static class NoticeListDto
    {
        private Long id;
        private UserInfoDto user;
        private String title;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

        public NoticeListDto(Notice entity)
        {
            this.id = entity.getId();
            this.user = new UserInfoDto(entity.getUser());
            this.title = entity.getTitle();
            this.createdDate = entity.getCreatedDate();
            this.lastModifiedDate = entity.getLastModifiedDate();
        }
    }
}
