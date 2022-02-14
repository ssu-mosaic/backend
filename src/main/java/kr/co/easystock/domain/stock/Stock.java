package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private String name;
    private int count;

    @Builder
    public Stock(User user, String name, int count)
    {
        this.user = user;
        this.name = name;
        this.count = count;
    }

    public void update(String name, int count)
    {
        this.name = name;
        this.count = count;
    }
}
