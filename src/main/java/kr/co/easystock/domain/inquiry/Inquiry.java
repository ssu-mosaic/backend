package kr.co.easystock.domain.inquiry;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.answer.Answer;
import kr.co.easystock.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Inquiry extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "inquiry", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer answer;

    private String title;
    private String content;

    @Builder
    public Inquiry(User user, String title, String content)
    {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(Inquiry inquiry)
    {
        this.title = inquiry.getTitle();
        this.content = inquiry.getContent();
    }

    // soft delete
    @Override
    public void delete()
    {
        super.delete();
    }

    // 연관관계 메서드
    public void answerToInquiry(Answer answer)
    {
        answer.mapInquiry(this);
        this.answer = answer;
    }
}
