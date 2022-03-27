package com.community.client.services;
import com.community.client.models.AdminViewAbout;
import com.community.client.repositories.AdminViewAboutRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AdminViewAboutService {
    //DI the repository
    private final AdminViewAboutRepository adminViewAboutRepository;

    public AdminViewAboutService(AdminViewAboutRepository adminViewAboutRepository) {
        this.adminViewAboutRepository = adminViewAboutRepository;
    }


    //This is the actual method which we will use in the controller
    public AdminViewAbout saveAdminViewAbout(AdminViewAbout adminViewAbout){
        AdminViewAbout saveAdminViewAbout = adminViewAboutRepository.save(adminViewAbout);
        return saveAdminViewAbout;
    }

    //create a method which gets you the update stored regarding the editing

    public Set<AdminViewAbout> getAllAdminViewAbout() {
        return adminViewAboutRepository.findAll();
    }
}
