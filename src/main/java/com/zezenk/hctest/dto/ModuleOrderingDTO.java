package com.zezenk.hctest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModuleOrderingDTO {
    private String moduleName;
    private Integer moduleOrder;
}
