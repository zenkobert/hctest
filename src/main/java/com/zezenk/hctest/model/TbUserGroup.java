package com.zezenk.hctest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "tb_user_group")
public class TbUserGroup implements Serializable {

    public TbUserGroup() {}

    public TbUserGroup(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup")
    private Set<TbModuleOrder> modules = new HashSet<>();

    public TbUserGroup addModule(TbModuleOrder module){
        module.setUserGroup(this);
        this.modules.add(module);
        return this;
    }
}
