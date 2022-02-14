package kr.co.easystock.domain.order;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.retailer.Retailer;
import kr.co.easystock.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor
public class Order extends BaseTimeEntity
{
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;

    @Builder
    public Order(User user, Retailer retailer)
    {
        this.user = user;
        this.retailer = retailer;
    }
}
