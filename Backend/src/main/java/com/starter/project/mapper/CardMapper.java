package com.starter.project.mapper;

import com.starter.project.dto.CardTypeDto;
import com.starter.project.model.CardType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    CardTypeDto toDto(CardType cardType);
    List<CardTypeDto> toDto(List<CardType> agencies);
    CardType toModel(CardTypeDto agencyDto);


}
