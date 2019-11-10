package com.zezenk.hctest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

//@Entity
//@Table(name = "vw_module_order")
public class VwModuleOrder implements Serializable {

//    @Id
    private Long id;

//    @Column(name = "user_group_id")
    private Long userGroupId;

//    @Column(name = "user_group")
    private String userGroup;

//    @Column(name = "module")
    private String module;

//    @Column(name = "ordering")
    private Integer ordering;
}
