package com.starter.project.dto;

public class LoginDto {
    String banque;
    String password;

    public String getBanque() {
        return banque;
    }

    public void setBanque(String banque) {
        this.banque = banque;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginDto(String banque, String password) {
        this.banque = banque;
        this.password = password;
    }

    @Override
    public String toString() {
        return "{" +
                "banque='" + banque + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
