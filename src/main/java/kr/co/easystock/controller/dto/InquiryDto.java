package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Getter;

import java.time.LocalDateTime;

public class InquiryDto
{
    @Getter
    public static class InquiryFormDto
    {
        private String title;
        private String content;
        private String category;

        public InquiryFormDto(String title, String content, String category)
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
    public static class InquiryResponseDto
    {
        private int id;
        private String title;
        private String content;
        private String category;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;
        private String answerContent;

        public InquiryResponseDto(Inquiry entity)
        {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.content = entity.getContent();
            this.category = entity.getCategory();
            this.createdDate = entity.getCreatedDate();
            this.lastModifiedDate = entity.getLastModifiedDate();
            if(entity.getAnswer() != null)
            {
                this.answerContent = entity.getAnswer().getContent();
            }
        }
    }

    @Getter
    public static class InquiryListResponseDto
    {
        private int id;
        private String title;
        private LocalDateTime createdDate;

        public InquiryListResponseDto(Inquiry entity)
        {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.createdDate = entity.getCreatedDate();
        }
    }
}
