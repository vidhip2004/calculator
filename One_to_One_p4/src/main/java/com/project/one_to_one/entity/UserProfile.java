package com.project.one_to_one.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProfileId;
    private String address;
    private String phone;
    private LocalDate dob;

    @OneToOne(mappedBy = "userProfile")
    @JsonIgnore
    private User user;

    public UserProfile() {
    }

    public Integer getProfileId() {
        return ProfileId;
    }

    public void setProfileId(Integer profileId) {
        ProfileId = profileId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
