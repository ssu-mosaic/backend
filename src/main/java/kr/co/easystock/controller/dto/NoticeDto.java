package kr.co.easystock.controller.dto;

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
        private String noticeTitle;
        private String noticeContent;

        public NoticeWriteRequestDto(String userId, String noticeTitle, String noticeContent)
        {
            this.userId = userId;
            this.noticeTitle = noticeTitle;
            this.noticeContent = noticeContent;
        }

        public Notice toEntity(User user)
        {
            return new Notice(user, noticeTitle, noticeContent);
        }
    }

    @Getter
    public static class NoticeUpdateRequestDto
    {
        private String userId;
        private String noticeTitle;
        private String noticeContent;
        private LocalDateTime noticeDate;
        private LocalDateTime noticeEditDate;

        public NoticeUpdateRequestDto(String userId, String noticeTitle, String noticeContent, LocalDateTime noticeDate, LocalDateTime noticeEditDate)
        {
            this.userId = userId;
            this.noticeTitle = noticeTitle;
            this.noticeContent = noticeContent;
            this.noticeDate = noticeDate;
            this.noticeEditDate = noticeEditDate;
        }

        public Notice toEntity()
        {
            return new Notice(null, noticeTitle, noticeContent);
        }
    }

    @Getter
    public static class NoticeViewDto
    {
        private Long noticeId;
        private String noticeTitle;
        private String noticeContent;
        private LocalDateTime noticeDate;
        private LocalDateTime noticeEditDate;

        public NoticeViewDto(Notice entity)
        {
            this.noticeId = entity.getId();
            this.noticeTitle = entity.getTitle();
            this.noticeContent = entity.getContent();
            this.noticeDate = entity.getCreatedDate();
            this.noticeEditDate = entity.getLastModifiedDate();
        }
    }

    @Getter
    public static class NoticeListDto
    {
        private Long noticeId;
        private String noticeTitle;
        private LocalDateTime noticeDate;
        private LocalDateTime noticeEditDate;

        public NoticeListDto(Notice entity)
        {
            this.noticeId = entity.getId();
            this.noticeTitle = entity.getTitle();
            this.noticeDate = entity.getCreatedDate();
            this.noticeEditDate = entity.getLastModifiedDate();
        }
    }
}
