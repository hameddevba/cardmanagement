package com.starter.project.mapper;

import com.starter.project.dto.AgencyDto;
import com.starter.project.model.Agency;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AgencyMapper {
    AgencyDto toDto(Agency agency);
    List<AgencyDto> toDto(List<Agency> agencies);
    Agency toModel(AgencyDto agencyDto);


}
