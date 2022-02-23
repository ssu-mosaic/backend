package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class AnswerDto
{
    @Getter
    public static class AnswerWriteRequestDto
    {
        private Long inquiryId;
        private String inquiryTitle;
        private String inquiryContent;
        private LocalDateTime inquiryDate;
        private String inquiryAnswer;
        private LocalDateTime inquiryAnsDate;

        @Builder
        public AnswerWriteRequestDto(Long inquiryId, String inquiryTitle, String inquiryContent, LocalDateTime inquiryDate, String inquiryAnswer, LocalDateTime inquiryAnsDate)
        {
            this.inquiryId = inquiryId;
            this.inquiryTitle = inquiryTitle;
            this.inquiryContent = inquiryContent;
            this.inquiryDate = inquiryDate;
            this.inquiryAnswer = inquiryAnswer;
            this.inquiryAnsDate = inquiryAnsDate;
        }

        public Answer toEntity(Inquiry inquiry)
        {
            return Answer.builder()
                    .inquiry(inquiry)
                    .content(inquiryContent)
                    .build();
        }
    }

    @Getter
    public static class AnswerUpdateRequestDto
    {
        private Long inquiryId;
        private String inquiryTitle;
        private String inquiryContent;
        private LocalDateTime inquiryDate;
        private String inquiryAnswer;
        private LocalDateTime inquiryAnsDate;

        @Builder
        public AnswerUpdateRequestDto(Long inquiryId, String inquiryTitle, String inquiryContent, LocalDateTime inquiryDate, String inquiryAnswer, LocalDateTime inquiryAnsDate)
        {
            this.inquiryId = inquiryId;
            this.inquiryTitle = inquiryTitle;
            this.inquiryContent = inquiryContent;
            this.inquiryDate = inquiryDate;
            this.inquiryAnswer = inquiryAnswer;
            this.inquiryAnsDate = inquiryAnsDate;
        }
    }

    @Getter
    public static class AnswerViewDto
    {
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifedDate;

        public AnswerViewDto(Answer entity)
        {
            this.content = entity.getContent();
            this.createdDate = entity.getCreatedDate();
            this.lastModifedDate = entity.getLastModifiedDate();
        }
    }
}
