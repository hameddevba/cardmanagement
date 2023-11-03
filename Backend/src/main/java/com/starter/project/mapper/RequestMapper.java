package com.starter.project.mapper;

import com.starter.project.dto.RequestDto;
import com.starter.project.model.Request;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    RequestDto toDto(Request cardType);
    List<RequestDto> toDto(List<Request> agencies);
    Request toModel(RequestDto agencyDto);


}
