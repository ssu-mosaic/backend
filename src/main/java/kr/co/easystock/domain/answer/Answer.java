package kr.co.easystock.domain.answer;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@NoArgsConstructor
public class Answer extends BaseTimeEntity
{
    @Id
    private int inquiryId;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Inquiry inquiry;
    private String user;
    private String content;

    public Answer(Inquiry inquiry, String user, String content)
    {
        this.inquiry = inquiry;
        this.inquiryId = inquiry.getId();
        this.user = user;
        this.content = content;
    }
}
