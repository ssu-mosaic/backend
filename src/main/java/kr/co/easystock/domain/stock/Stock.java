package kr.co.easystock.domain.stock;

import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Stock
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "userId")
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
}
