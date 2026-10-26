package com.project.one_to_one.entity;

import jakarta.persistence.*;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String email;

    //CascadeType.ALL ALL means Save,update,delete,Merge,Refresh include all opertation
    @OneToOne(cascade = CascadeType.ALL)//Whatever operation you perform on User, Hibernate will perform the same operation on UserProfile

    @JoinColumn(name="profile_id")//Create a foreign key column named profile_id in the User table.
    private UserProfile userProfile;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}
