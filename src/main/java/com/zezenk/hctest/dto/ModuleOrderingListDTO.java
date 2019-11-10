package com.zezenk.hctest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModuleOrderingListDTO {
    private List<ModuleOrderingDTO> modules;
}
