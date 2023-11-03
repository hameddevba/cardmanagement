package com.starter.project.dto;

import java.util.Set;

public class UserDetailDto {
    private String username;

    public UserDetailDto(String username, String email, Set<String> roles) {
        this.username = username;
        this.email = email;
        this.roles = roles;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    private String email;
    private Set<String> roles;
}
