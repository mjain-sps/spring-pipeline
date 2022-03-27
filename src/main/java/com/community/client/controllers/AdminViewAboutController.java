package com.community.client.controllers;
import com.community.client.models.AdminViewAbout;
import com.community.client.services.AdminViewAboutService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class AdminViewAboutController {
    //DI for the Service Layer
    private final AdminViewAboutService adminViewAboutService;

    public AdminViewAboutController(AdminViewAboutService adminViewAboutService) {
        this.adminViewAboutService = adminViewAboutService;
    }
        //API end point will be defined here
        //1.End Point Is to Save the user's information and Enquires
        @PostMapping("/api/new-adminViewAbout")
        public AdminViewAbout saveNewAdminViewAbout(@RequestBody AdminViewAbout adminViewAbout) {
        System.out.println("Im am being hit");
            return adminViewAboutService.saveAdminViewAbout(adminViewAbout);
        }

        //End Point to get all the Admin Editing 'About' page
        @GetMapping("api/adminViewAbout")
        private Set<AdminViewAbout> getAllAdminViewAbout(){
        return adminViewAboutService.getAllAdminViewAbout();
        }


}
