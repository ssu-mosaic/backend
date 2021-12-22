package kr.co.easystock.domain.order;

import kr.co.easystock.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Order
{
    @Id
    int id;
    String name;
}
