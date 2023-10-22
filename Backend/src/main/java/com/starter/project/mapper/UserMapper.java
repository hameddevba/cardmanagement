package com.starter.project.mapper;

import com.starter.project.dto.UserDetailDto;
import com.starter.project.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDetailDto toDto(User user);
    List<UserDetailDto> toDto(List<User> user);
    User toModel(UserDetailDto userDto);


}
