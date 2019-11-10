package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbModule;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ModuleRepository extends CrudRepository<TbModule, Long> {

    Optional<TbModule> findByName(String name);
}
