package com.zezenk.hctest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "tb_module_order")
public class TbModuleOrder implements Serializable {

    public TbModuleOrder() {}

    public TbModuleOrder(TbModule module, Integer ordering) {
        this.module = module;
        this.ordering = ordering;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TbModule module;

    private Integer ordering;

    @ManyToOne
    private TbUserGroup userGroup;
}
