package com.starter.project.mapper;

import com.starter.project.dto.RoleDto;
import com.starter.project.model.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    RoleDto toDto(Role role);
    List<RoleDto> toDto(List<Role> role);
    Role toModel(RoleDto roleDto);


}
