package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.PersistenceUnitUtil;
import java.time.LocalDateTime;

public class InquiryDto
{
    @Getter
    public static class InquiryWriteRequestDto
    {
        private String userId;
        private String title;
        private String content;
        private String category;

        @Builder
        public InquiryWriteRequestDto(String userId, String title, String content, String category)
        {
            this.userId = userId;
            this.title = title;
            this.content = content;
            this.category = category;
        }

        public Inquiry toEntity(User user)
        {
            return Inquiry.builder()
                    .user(user)
                    .title(title)
                    .content(content)
                    .category(category)
                    .build();
        }
    }

    @Getter
    public static class InquiryUpdateRequestDto
    {
        private String category;
        private String title;
        private String content;

        @Builder
        public InquiryUpdateRequestDto(String title, String content, String category)
        {
            this.title = title;
            this.content = content;
            this.category = category;
        }

        public Inquiry toEntity()
        {
            return Inquiry.builder()
                    .title(title)
                    .content(content)
                    .category(category)
                    .build();
        }
    }

    @Getter
    public static class InquiryViewDto
    {
        private Long id;
        private String title;
        private String content;
        private String category;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
        private String answerContent;
        private LocalDateTime answerCreatedDate;
        private LocalDateTime answerLastModifiedDate;

        public InquiryViewDto(Inquiry entity)
        {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.category = entity.getCategory();
            this.createdDate = entity.getCreatedDate();
            this.lastModifiedDate = entity.getLastModifiedDate();

            Answer answer = entity.getAnswer();
            if(answer != null)
            {
                this.answerContent = answer.getContent();
                this.answerCreatedDate = answer.getCreatedDate();
                this.answerLastModifiedDate = answer.getLastModifiedDate();
            }
        }
    }

    @Getter
    public static class InquiryListDto
    {
        private Long id;
        private String title;
        private String category;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
        private boolean isAnswered;

        public InquiryListDto(Inquiry entity)
        {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.category = entity.getCategory();
            this.createdDate = entity.getCreatedDate();
            this.lastModifiedDate = entity.getLastModifiedDate();

            isAnswered = (entity.getAnswer() != null) ? true : false;
        }
    }
}
