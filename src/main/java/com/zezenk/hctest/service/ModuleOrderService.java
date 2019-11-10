package com.zezenk.hctest.service;

import com.zezenk.hctest.dto.ModuleOrderingListDTO;

public interface ModuleOrderService {

    ModuleOrderingListDTO getModuleOrderingByUserId(Long userId);
}
