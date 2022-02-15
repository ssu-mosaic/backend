package kr.co.easystock.domain.notice;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Notice extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    public Notice(User user, String title, String content)
    {
        this.user = user;
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }

    // soft delete
    @Override
    public void delete()
    {
        super.delete();
    }
}
