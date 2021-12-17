package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Getter;
import lombok.Setter;

public class AnswerDto
{
    @Getter
    @Setter
    public static class AnswerFormDto
    {
        private Inquiry inquiry;
        private String content;

        public AnswerFormDto(String content)
        {
            this.content = content;
        }

        public Answer toEntity()
        {
            return Answer.builder()
                    .inquiry(inquiry)
                    .content(content)
                    .build();
        }
    }
}
