package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbUser;
import com.zezenk.hctest.model.TbUserGroup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserGroupRepositoryIT {

    @Autowired
    UserGroupRepository userGroupRepository;

    @Test
    public void findByName() {
        Optional<TbUserGroup> userGroupOptional = userGroupRepository.findByName("groupA");

        assertEquals("groupA", userGroupOptional.get().getName());
    }
}