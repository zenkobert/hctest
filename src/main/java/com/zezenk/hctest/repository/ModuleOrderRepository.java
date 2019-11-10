package com.zezenk.hctest.repository;

import com.zezenk.hctest.model.TbModuleOrder;
import com.zezenk.hctest.model.TbUserGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ModuleOrderRepository extends CrudRepository<TbModuleOrder, Long> {

    List<TbModuleOrder> findByUserGroupOrderByOrdering(TbUserGroup group);
}
