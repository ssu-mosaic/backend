package kr.co.easystock.domain.order;

import kr.co.easystock.domain.Item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class OrderItem
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    // 주문 상태[ONPROGRESS, CANCELED, COMPLETED]
    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;

    private int totalPrice;
    private int count;

    public OrderItem(Order order, Item item, int count)
    {
        this.order = order;
        this.item = item;
        this.totalPrice = item.getPrice() * count;
        this.count = count;
        this.status = OrderStatus.ONPROGRESS;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return getId().equals(orderItem.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    /**
     * 상품 주문 취소
     */
    public void cancel()
    {
        this.status = OrderStatus.CANCELED;
    }

    /**
     * 상품 수취 완료
     */
    public void complete()
    {
        this.status = OrderStatus.COMPLETED;
    }


    public void mapOrder(Order order)
    {
        this.order = order;
    }
}
