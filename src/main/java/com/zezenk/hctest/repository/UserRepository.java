package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<TbUser, Long> {

    Optional<TbUser> findByName(String name);
}
