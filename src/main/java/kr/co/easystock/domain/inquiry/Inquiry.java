package kr.co.easystock.domain.inquiry;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.answer.Answer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Inquiry extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String title;
    private String content;
    @OneToOne(mappedBy = "inquiry")
    private Answer answer;

    @Builder
    public Inquiry(String category, String title, String content)
    {
        this.category = category;
        this.title = title;
        this.content = content;
    }
}
