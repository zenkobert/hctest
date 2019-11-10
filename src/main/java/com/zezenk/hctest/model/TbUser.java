package com.zezenk.hctest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "tb_user")
public class TbUser implements Serializable {

    public TbUser(){}

    public TbUser(String name, TbUserGroup group) {
        this.name = name;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne
    private TbUserGroup group;
}
