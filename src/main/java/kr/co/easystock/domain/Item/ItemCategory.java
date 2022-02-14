package kr.co.easystock.domain.Item;

import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WOOSERK.
 * User: WOOSERK
 * Date: 2022-02-11
 * Time: 오후 8:21
 */

@Getter
@Entity
public class ItemCategory
{
    @Id @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ItemCategory parent;

    @OneToMany(mappedBy = "parent")
    private List<ItemCategory> child = new ArrayList<>();
}

