package com.zezenk.hctest.controller;

import com.zezenk.hctest.dto.ModuleOrderingDTO;
import com.zezenk.hctest.dto.ModuleOrderingListDTO;
import com.zezenk.hctest.exception.NotFoundException;
import com.zezenk.hctest.exception.ParsingException;
import com.zezenk.hctest.exception.RestResponseEntityExceptionHandler;
import com.zezenk.hctest.service.ModuleOrderService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ModuleOrderControllerTest {

    @Mock
    ModuleOrderService moduleOrderService;

    @InjectMocks
    ModuleOrderController moduleOrderController;

    MockMvc mockMvc;

    ModuleOrderingListDTO orderingListDTO;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(moduleOrderController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

        ModuleOrderingDTO dto1 = new ModuleOrderingDTO("PromoCard", 1);
        ModuleOrderingDTO dto2 = new ModuleOrderingDTO("CategoryCard", 2);
        ModuleOrderingDTO dto3 = new ModuleOrderingDTO("FlashSaleCard", 3);
        ModuleOrderingDTO dto4 = new ModuleOrderingDTO("HistoryCard", 4);
        ModuleOrderingDTO dto5 = new ModuleOrderingDTO("NewsCard", 5);

        orderingListDTO = new ModuleOrderingListDTO(Arrays.asList(dto1, dto2, dto3, dto4, dto5));
    }

    @Test
    public void whenFindByUserId_thenShouldHaveFiveRecord() throws Exception {

        when(moduleOrderService.getModuleOrderingByUserId(anyLong())).thenReturn(orderingListDTO);

        mockMvc.perform(get(ModuleOrderController.BASE_URL + "?userID=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modules", hasSize(5)));
    }

    @Test
    public void whenFindByUserId_thenShouldBeInCorrectOrdering() throws Exception {

        when(moduleOrderService.getModuleOrderingByUserId(anyLong())).thenReturn(orderingListDTO);

        mockMvc.perform(get(ModuleOrderController.BASE_URL + "?userID=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.modules[0].moduleOrder", is(1)))
                .andExpect(jsonPath("$.modules[0].moduleName", is("PromoCard")))
                .andExpect(jsonPath("$.modules[1].moduleOrder", is(2)))
                .andExpect(jsonPath("$.modules[1].moduleName", is("CategoryCard")))
                .andExpect(jsonPath("$.modules[2].moduleOrder", is(3)))
                .andExpect(jsonPath("$.modules[2].moduleName", is("FlashSaleCard")))
                .andExpect(jsonPath("$.modules[3].moduleOrder", is(4)))
                .andExpect(jsonPath("$.modules[3].moduleName", is("HistoryCard")))
                .andExpect(jsonPath("$.modules[4].moduleOrder", is(5)))
                .andExpect(jsonPath("$.modules[4].moduleName", is("NewsCard")));
    }

    @Test
    public void whenUserNotFound_thenShouldReturn404Error() throws Exception {
        when(moduleOrderService.getModuleOrderingByUserId(anyLong())).thenThrow(NotFoundException.class);

        mockMvc.perform(get(ModuleOrderController.BASE_URL + "?userID=1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenUserIdIsNotNumeric_thenShouldReturn400Error() throws Exception {
        when(moduleOrderService.getModuleOrderingByUserId(anyLong())).thenThrow(ParsingException.class);

        mockMvc.perform(get(ModuleOrderController.BASE_URL + "?userID=x")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}