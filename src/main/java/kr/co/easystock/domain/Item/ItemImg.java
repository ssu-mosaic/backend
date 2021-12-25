package kr.co.easystock.domain.Item;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class ItemImg
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
