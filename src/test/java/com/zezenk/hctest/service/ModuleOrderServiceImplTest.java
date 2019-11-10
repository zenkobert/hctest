package com.zezenk.hctest.service;

import com.zezenk.hctest.dto.ModuleOrderingDTO;
import com.zezenk.hctest.dto.ModuleOrderingListDTO;
import com.zezenk.hctest.model.TbModule;
import com.zezenk.hctest.model.TbModuleOrder;
import com.zezenk.hctest.model.TbUser;
import com.zezenk.hctest.model.TbUserGroup;
import com.zezenk.hctest.repository.ModuleOrderRepository;
import com.zezenk.hctest.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ModuleOrderServiceImplTest {
    ModuleOrderServiceImpl moduleOrderService;

    @Mock
    UserRepository userRepository;

    @Mock
    ModuleOrderRepository moduleOrderRepository;

    List<TbModuleOrder> modules;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        moduleOrderService = new ModuleOrderServiceImpl(moduleOrderRepository, userRepository);

        TbModule promoModule = new TbModule(1L, "PromoCard");
        TbModule categoryModule = new TbModule(2L, "CategoryCard");
        TbModule flashSaleModule = new TbModule(3L, "FlashSaleCard");
        TbModule historyModule = new TbModule(4L, "HistoryCard");
        TbModule newsModule = new TbModule(5L, "NewsCard");

        TbModuleOrder moduleOrder1 = new TbModuleOrder(promoModule, 1);
        TbModuleOrder moduleOrder2 = new TbModuleOrder(categoryModule, 2);
        TbModuleOrder moduleOrder3 = new TbModuleOrder(flashSaleModule, 3);
        TbModuleOrder moduleOrder4 = new TbModuleOrder(historyModule, 4);
        TbModuleOrder moduleOrder5 = new TbModuleOrder(newsModule, 5);

        modules = Arrays.asList(moduleOrder1, moduleOrder2, moduleOrder3, moduleOrder4, moduleOrder5);

        TbUser user = new TbUser(1L, "Albert", new TbUserGroup(1L, "groupA"));

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(moduleOrderRepository.findByUserGroupOrderByOrdering(any(TbUserGroup.class))).thenReturn(modules);
    }

    @Test
    public void whenFindByUserId_thenShouldHaveFiveRecord() {
        ModuleOrderingListDTO orderingListDTO = moduleOrderService.getModuleOrderingByUserId(1L);
        assertEquals(5, orderingListDTO.getModules().size());
    }

    @Test
    public void whenFindByUserId_thenShouldBeInAscendingOrdering() {
        ModuleOrderingListDTO orderingListDTO = moduleOrderService.getModuleOrderingByUserId(1L);

        for (Integer i = 1; i <= orderingListDTO.getModules().size(); i++) {
            assertEquals(i, orderingListDTO.getModules().get(i-1).getModuleOrder());
        }
    }

    @Test
    public void whenFindByUserId_thenShouldHaveDistinctModule() {
        ModuleOrderingListDTO orderingListDTO = moduleOrderService.getModuleOrderingByUserId(1L);
        Set<String> set = new HashSet<>();

        for (ModuleOrderingDTO dto : orderingListDTO.getModules()) {
            set.add(dto.getModuleName());
        }

        assertEquals(set.size(), orderingListDTO.getModules().size());
    }
}