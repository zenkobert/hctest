package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbUserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserGroupRepository extends CrudRepository<TbUserGroup, Long> {

    Optional<TbUserGroup> findByName(String name);
}
