package com.tynadmin.controller;

import com.tynadmin.service.AdminService;
import com.tynentity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AdminRestController {

    private final AdminService adminService;

    public AdminRestController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/check-email")
    public String checkEmailExists(String email, String id) {
        Admin admin = adminService.getByEmail(email);
        int adminId = 0;

        if(!id.isEmpty()) {
            adminId = Integer.parseInt(id);
        }

        if(admin != null && admin.getId() == adminId) {
            return "OK";
        }

        return admin == null ? "OK" : "NOT OK";
    }

    @GetMapping("/change-status")
    public String changeStatus(String id) {
        int adminId = 0;

        if(!id.isEmpty()) {
            adminId = Integer.parseInt(id);
        }

        Admin admin = adminService.get(adminId);
        adminService.updateStatus(admin);

        return "OK";
    }
}
