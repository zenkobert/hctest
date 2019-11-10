package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbModule;
import com.zezenk.hctest.model.TbUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ModuleRepositoryIT {

    @Autowired
    ModuleRepository moduleRepository;

    @Test
    public void findByName() {
        Optional<TbModule> moduleOptional = moduleRepository.findByName("PromoCard");

        assertEquals("PromoCard", moduleOptional.get().getName());
    }
}