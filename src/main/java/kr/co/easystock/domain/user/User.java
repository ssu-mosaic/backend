package kr.co.easystock.domain.user;

import kr.co.easystock.domain.BaseTimeEntity;
import kr.co.easystock.domain.cart.Cart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity
{
    @Id
    private String id;

    private String password;

    @Column(length = 10)
    private String businessNo;

    private String name;

    @Column(unique = true)
    private String email;

    private String address;
    private String phone;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Cart cart;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String id, String password, String name, String businessNo, String email, String address, String phone) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.businessNo = businessNo;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    // 연관관계 메서드
    public void assignCart(Cart cart)
    {
        this.cart = cart;
        cart.mapUser(this);
    }

    // id는 변경할 수 없음
    public void update(User user)
    {
        this.password = user.getPassword();
        this.name = user.getName();
        this.businessNo = user.getBusinessNo();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.phone = user.getPhone();
    }

    // 비밀번호 변경 전용 메서드
    public void updatePassword(String password)
    {
        this.password = password;
    }

    // soft delete
    @Override
    public void delete()
    {
        super.delete();
    }
}
