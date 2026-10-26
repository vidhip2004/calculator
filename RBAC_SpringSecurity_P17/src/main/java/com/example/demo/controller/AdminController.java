package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class AdminController {

    // Simple in-memory resource
    private final List<AdminData> adminDataList = new ArrayList<>();

    private int nextId = 1;

    // GET - ADMIN only
    @GetMapping("/api/admin/data")
    //Without it, @PreAuthorize won't enforce the role restriction.
    //Only a user having ROLE_ADMIN can access this method
    @PreAuthorize("hasRole('ADMIN')")
    public List<AdminData> getAdminData() {
        return adminDataList;
    }

    // GET - ADMIN and USER
    @GetMapping("/api/user/data")
  //Allow either ADMIN or USER.
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String getUserData() {
        return "User data accessed successfully!";
    }

    // POST - ADMIN only
    @PostMapping("/api/admin/data")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminData addAdminData(@RequestBody AdminData data) {

        data.setId(nextId++);
        adminDataList.add(data);

        return data;
    }

    // PUT - ADMIN only
    @PutMapping("/api/admin/data/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminData updateAdminData(
            @PathVariable int id,
            @RequestBody AdminData updatedData) {

        for (AdminData data : adminDataList) {

            if (data.getId() == id) {

                data.setLabel(updatedData.getLabel());

                return data;
            }
        }

        return null;
    }

    // DELETE - ADMIN only
    @DeleteMapping("/api/admin/data/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAdminData(@PathVariable int id) {

        for (AdminData data : adminDataList) {

            if (data.getId() == id) {

                adminDataList.remove(data);

                return "Admin data deleted successfully!";
            }
        }

        return "Data not found!";
    }

    //What is AdminData? We don't create a separate entity because your assignment says: simple in-memory admin-only resource
    // Simple class for in-memory data
    public static class AdminData {

        private int id;
        private String label;

        public AdminData() {
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}