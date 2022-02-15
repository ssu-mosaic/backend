package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class AnswerDto
{
    @Getter
    public static class AnswerWriteRequestDto
    {
        private String content;

        public AnswerWriteRequestDto(String content)
        {
            this.content = content;
        }

        public Answer toEntity(Inquiry inquiry)
        {
            return Answer.builder()
                    .inquiry(inquiry)
                    .content(content)
                    .build();
        }
    }

    @Getter
    public static class AnswerUpdateRequestDto
    {
        private String content;

        public AnswerUpdateRequestDto(String content)
        {
            this.content = content;
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
