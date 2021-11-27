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
@SecondaryTable(
        name = "inquiryAnswer",
        pkJoinColumns = @PrimaryKeyJoinColumn(
                name = "inquiryId", referencedColumnName = "id"
        )
)
public class Inquiry extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String category;
    private String title;
    private String content;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "user", column = @Column(name = "aUser")),
            @AttributeOverride(name = "content", column = @Column(name = "aContent")),
            @AttributeOverride(name = "createdDate", column = @Column(name = "aCreatedDate")),
            @AttributeOverride(name = "lastModifiedDate", column = @Column(name = "aLastModifiedDate"))
    })
    private Answer answer;

    @Builder
    public Inquiry(String category, String title, String content, Answer answer)
    {
        this.category = category;
        this.title = title;
        this.content = content;
        this.answer = answer;
    }
}
