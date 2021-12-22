package kr.co.easystock.domain.order;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order
{
    @Id
    Long id;
    String name;
}
