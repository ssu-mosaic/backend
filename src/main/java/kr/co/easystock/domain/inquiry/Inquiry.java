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

    @OneToOne(mappedBy = "inquiry", fetch = FetchType.LAZY)
    private Answer answer;

    private String title;
    private String content;
    private String category;

    @Builder
    public Inquiry(User user, String title, String content, String category)
    {
        this.user = user;
        this.title = title;
        this.content = content;
        this.category = category;
    }

    public void update(Inquiry entity)
    {
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.category = entity.getCategory();
    }

    @Override
    public void delete()
    {
        super.delete();
    }

    // 연관관계 메서드
    public void mapAnswer(Answer answer)
    {
        this.answer = answer;
    }
}
