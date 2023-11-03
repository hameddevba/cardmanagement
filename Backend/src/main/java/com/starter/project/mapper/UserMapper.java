package com.starter.project.mapper;

import com.starter.project.dto.UserDetailDto;
import com.starter.project.model.Role;
import com.starter.project.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDetailDto toDto(User user);
    List<UserDetailDto> toDto(List<User> user);
    User toModel(UserDetailDto userDto);

    // Map String to Word
    @Mapping(source = "value", target = "name")
    Role stringToRole(String value);

    // Map Word to string
    default String roleToString(Role role) {
        return (role == null) ? null : role.getName().name();
    }

}
