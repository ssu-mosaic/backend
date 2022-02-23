package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.inquiry.Inquiry;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static kr.co.easystock.controller.dto.AnswerDto.AnswerViewDto;

public class InquiryDto
{
    @Getter
    public static class InquiryWriteRequestDto
    {
        private String userId;
        private String inquiryTitle;
        private String inquiryContent;

        @Builder
        public InquiryWriteRequestDto(String userId, String inquiryTitle, String inquiryContent)
        {
            this.userId = userId;
            this.inquiryTitle = inquiryTitle;
            this.inquiryContent = inquiryContent;
        }

        public Inquiry toEntity(User user)
        {
            return Inquiry.builder()
                    .user(user)
                    .title(inquiryTitle)
                    .content(inquiryContent)
                    .build();
        }
    }

    @Getter
    public static class InquiryUpdateRequestDto
    {
        private Long inquiryId;
        private String inquiryTitle;
        private LocalDateTime inquiryDate;
        private String inquiryContent;
        private String inquiryAnswer;
        private LocalDateTime inquiryAnsDate;

        @Builder
        public InquiryUpdateRequestDto(Long inquiryId, String inquiryTitle, LocalDateTime inquiryDate, String inquiryContent, String inquiryAnswer, LocalDateTime inquiryAnsDate)
        {
            this.inquiryId = inquiryId;
            this.inquiryTitle = inquiryTitle;
            this.inquiryDate = inquiryDate;
            this.inquiryContent = inquiryContent;
            this.inquiryAnswer = inquiryAnswer;
            this.inquiryAnsDate = inquiryAnsDate;
        }

        public Inquiry toEntity()
        {
            return Inquiry.builder()
                    .title(inquiryTitle)
                    .content(inquiryContent)
                    .build();
        }
    }

    @Getter
    public static class InquiryViewDto
    {
        private Long inquiryId;
        private String inquiryTitle;
        private String inquiryContent;
        private LocalDateTime inquiryDate;
        private String inquiryAnswer;
        private LocalDateTime inquiryAnsDate;

        public InquiryViewDto(Inquiry entity)
        {
            this.inquiryId = entity.getId();
            this.inquiryTitle = entity.getTitle();
            this.inquiryContent = entity.getContent();
            this.inquiryDate = entity.getCreatedDate();

            if(entity.getAnswer() != null)
            {
                this.inquiryAnswer = entity.getAnswer().getContent();
                this.inquiryAnsDate = entity.getAnswer().getCreatedDate();
            }
        }
    }

    @Getter
    public static class InquiryListDto
    {
        private Long inquiryId;
        private String inquiryTitle;
        private LocalDateTime inquiryDate;
        private String inquiryAnswer;
        private LocalDateTime inquiryAnsDate;

        public InquiryListDto(Inquiry entity)
        {
            this.inquiryId = entity.getId();
            this.inquiryTitle = entity.getTitle();
            this.inquiryDate = entity.getCreatedDate();

            if(entity.getAnswer() != null)
            {
                this.inquiryAnswer = entity.getAnswer().getContent();
                this.inquiryAnsDate = entity.getAnswer().getCreatedDate();
            }
        }
    }
}
