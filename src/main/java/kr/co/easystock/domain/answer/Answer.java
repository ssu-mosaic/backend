package kr.co.easystock.domain.answer;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NoArgsConstructor
@Getter
public class Answer extends BaseTimeEntity
{
    @Id
    private int inquiryId;

    @OneToOne
    @Cascade(CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Inquiry inquiry;
    private String content;

    @Builder
    public Answer(Inquiry inquiry, String content)
    {
        this.inquiry = inquiry;
        this.inquiryId = inquiry.getId();
        this.content = content;
    }
}
