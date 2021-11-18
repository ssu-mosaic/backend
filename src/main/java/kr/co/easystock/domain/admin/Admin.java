package kr.co.easystock.domain.admin;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Admin
{
    @Id
    private int id;
    private String email;
    private String password;
}
