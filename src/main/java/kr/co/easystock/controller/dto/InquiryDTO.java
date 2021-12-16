package kr.co.easystock.controller.dto;

import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Getter;

public class InquiryDTO
{
    @Getter
    public static class InquiryFormDTO
    {
        private String title;
        private String content;
        private String category;

        public InquiryFormDTO(String title, String content, String category)
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
}
