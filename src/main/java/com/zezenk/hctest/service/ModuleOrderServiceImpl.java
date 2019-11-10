package com.zezenk.hctest.service;

import com.zezenk.hctest.dto.ModuleOrderingDTO;
import com.zezenk.hctest.dto.ModuleOrderingListDTO;
import com.zezenk.hctest.exception.NotFoundException;
import com.zezenk.hctest.model.TbModuleOrder;
import com.zezenk.hctest.model.TbUser;
import com.zezenk.hctest.repository.ModuleOrderRepository;
import com.zezenk.hctest.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleOrderServiceImpl implements ModuleOrderService{

    private final ModuleOrderRepository moduleOrderRepository;
    private final UserRepository userRepository;

    public ModuleOrderServiceImpl(ModuleOrderRepository moduleOrderRepository, UserRepository userRepository) {
        this.moduleOrderRepository = moduleOrderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ModuleOrderingListDTO getModuleOrderingByUserId(Long userId) {
        Optional<TbUser> optionalTbUser = userRepository.findById(userId);


        if(optionalTbUser.isPresent()) {
            TbUser user = optionalTbUser.get();
            List<TbModuleOrder> moduleOrders = moduleOrderRepository.findByUserGroupOrderByOrdering(user.getGroup());

            return convertToDTO(moduleOrders);
        } else {
            throw new NotFoundException("User ID not found");
        }
    }

    private ModuleOrderingListDTO convertToDTO(List<TbModuleOrder> moduleOrders) {
        List<ModuleOrderingDTO> modules = new ArrayList<>();

        for(TbModuleOrder moduleOrder : moduleOrders) {
            modules.add(new ModuleOrderingDTO(moduleOrder.getModule().getName(),moduleOrder.getOrdering()));
        }

        return new ModuleOrderingListDTO(modules);
    }
}
