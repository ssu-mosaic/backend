package kr.co.easystock.domain.home;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.DateTemplate;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import static kr.co.easystock.domain.order.QOrder.*;
import static kr.co.easystock.domain.order.QOrderItem.orderItem;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-28
 * Time: 오후 5:46
 */

@Repository
public class HomeRepository
{
    private final JPAQueryFactory query;
    private final EntityManager em;

    public HomeRepository(EntityManager em)
    {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    public List<Tuple> orderDayStats(String userId)
    {
        StringTemplate stringTemplate = Expressions.stringTemplate("DATE({0})", order.createdDate);

        return query.selectDistinct(stringTemplate, orderItem.totalPrice.sum())
                .from(orderItem)
                .join(order)
                .on(order.id.eq(orderItem.order.id))
                .where(order.user.id.eq(userId))
                .groupBy(stringTemplate)
                .fetch();
    }

    public List<Tuple> orderStatusStat(String userId)
    {
        return query.select(orderItem.status, order.count())
                .from(orderItem)
                .join(order)
                .on(order.id.eq(orderItem.order.id))
                .where(order.user.id.eq(userId))
                .groupBy(orderItem.status)
                .fetch();
    }
}
