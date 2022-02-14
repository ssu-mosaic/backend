package kr.co.easystock.domain.answer;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Answer extends BaseTimeEntity
{
    @Id
    @Column(name = "inquiry_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    private String content;

    @Builder
    public Answer(Inquiry inquiry, String content)
    {
        this.inquiry = inquiry;
        this.content = content;
    }

    public void update(String content)
    {
        this.content = content;
    }

    // 연관관계 메서드
    public void answerToInquiry(Inquiry inquiry)
    {
        inquiry.mapAnswer(this);
        this.inquiry = inquiry;
    }
}
