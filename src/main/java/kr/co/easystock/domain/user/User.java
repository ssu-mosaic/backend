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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
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

    // soft delete
    @Override
    public void delete()
    {
        super.delete();
    }
}
