package com.starter.project.dto;

import com.starter.project.model.enums.RoleEnum;

public class RoleDto {
    public RoleDto(Integer id, RoleEnum name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RoleEnum getName() {
        return name;
    }

    public void setName(RoleEnum name) {
        this.name = name;
    }

    private Integer id;
    private RoleEnum name;
}
