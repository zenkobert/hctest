package com.zezenk.hctest.controller;

import com.zezenk.hctest.dto.ModuleOrderingListDTO;
import com.zezenk.hctest.exception.ParsingException;
import com.zezenk.hctest.service.ModuleOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ModuleOrderController.BASE_URL)
public class ModuleOrderController {

    public static final String BASE_URL = "/api/v1/moduleorders";

    private final ModuleOrderService moduleOrderService;

    public ModuleOrderController(ModuleOrderService moduleOrderService) {
        this.moduleOrderService = moduleOrderService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ModuleOrderingListDTO getModuleOrderByUserId(@RequestParam(name = "userID") String userId) {
        try {
            ModuleOrderingListDTO list = moduleOrderService.getModuleOrderingByUserId(Long.parseLong(userId));
            return list;
        } catch (NumberFormatException e) {
            throw new ParsingException("userId must be Numeric");
        }
    }
}
