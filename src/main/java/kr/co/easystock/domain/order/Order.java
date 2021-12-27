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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "business_no")
    private User user;
    @ManyToOne
    @JoinColumn(name = "retailer_id")
    private Retailer retailer;
    private String detail;

    @Builder
    public Order(User user, Retailer retailer, String detail)
    {
        this.user = user;
        this.retailer = retailer;
        this.detail = detail;
    }
}
