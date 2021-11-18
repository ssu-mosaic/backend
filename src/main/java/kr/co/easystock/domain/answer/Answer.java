package kr.co.easystock.domain.answer;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.inquiry.Inquiry;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Answer extends BaseTimeEntity
{
    @Id
    private int id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Inquiry inquiry;
    private String content;
    @CreatedDate
    private LocalDateTime date;

    public Answer(Inquiry inquiry, String content)
    {
        this.id = inquiry.getId();
        this.inquiry = inquiry;
        this.content = content;
    }
}
