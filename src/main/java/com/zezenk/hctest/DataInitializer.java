package com.zezenk.hctest;

import com.zezenk.hctest.model.TbModule;
import com.zezenk.hctest.model.TbModuleOrder;
import com.zezenk.hctest.model.TbUser;
import com.zezenk.hctest.model.TbUserGroup;
import com.zezenk.hctest.repository.ModuleRepository;
import com.zezenk.hctest.repository.UserGroupRepository;
import com.zezenk.hctest.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final UserGroupRepository userGroupRepository;

    public DataInitializer(UserRepository userRepository, ModuleRepository moduleRepository, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        TbUserGroup groupA = userGroupRepository.findByName("groupA").get();
        TbUserGroup groupB = userGroupRepository.findByName("groupB").get();
        TbUserGroup groupC = userGroupRepository.findByName("groupC").get();

        initializeUser(groupA, groupB, groupC);
        initializeModuleOrder(groupA, groupB, groupC);
    }

    private void initializeModuleOrder(TbUserGroup groupA, TbUserGroup groupB, TbUserGroup groupC) {
        TbModule modulePromo = moduleRepository.findByName("PromoCard").get();
        TbModule moduleCategory = moduleRepository.findByName("CategoryCard").get();
        TbModule moduleFlashSale = moduleRepository.findByName("FlashSaleCard").get();
        TbModule moduleHistory = moduleRepository.findByName("HistoryCard").get();
        TbModule moduleNews = moduleRepository.findByName("NewsCard").get();

        groupA
                .addModule(new TbModuleOrder(modulePromo, 1))
                .addModule(new TbModuleOrder(moduleCategory, 2))
                .addModule(new TbModuleOrder(moduleFlashSale, 3))
                .addModule(new TbModuleOrder(moduleHistory, 4))
                .addModule(new TbModuleOrder(moduleNews, 5));

        groupB
                .addModule(new TbModuleOrder(modulePromo, 1))
                .addModule(new TbModuleOrder(moduleHistory, 2))
                .addModule(new TbModuleOrder(moduleFlashSale, 3))
                .addModule(new TbModuleOrder(moduleCategory, 4))
                .addModule(new TbModuleOrder(moduleNews, 5));

        groupC
                .addModule(new TbModuleOrder(modulePromo, 1))
                .addModule(new TbModuleOrder(moduleFlashSale, 2))
                .addModule(new TbModuleOrder(moduleCategory, 3))
                .addModule(new TbModuleOrder(moduleNews, 4))
                .addModule(new TbModuleOrder(moduleHistory, 5));

        userGroupRepository.save(groupA);
        userGroupRepository.save(groupB);
        userGroupRepository.save(groupC);
    }

    private void initializeUser(TbUserGroup groupA, TbUserGroup groupB, TbUserGroup groupC){
        TbUser user1 = new TbUser("user1", groupA);
        TbUser user2 = new TbUser("user2", groupA);
        TbUser user3 = new TbUser("user3", groupA);
        TbUser user4 = new TbUser("user4", groupB);
        TbUser user5 = new TbUser("user5", groupB);
        TbUser user6 = new TbUser("user6", groupB);
        TbUser user7 = new TbUser("user7", groupC);
        TbUser user8 = new TbUser("user8", groupC);
        TbUser user9 = new TbUser("user9", groupC);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
    }
}
