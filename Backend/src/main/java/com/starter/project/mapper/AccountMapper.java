package com.starter.project.mapper;

import com.starter.project.dto.AccountDto;
import com.starter.project.model.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring",uses = {AgencyMapper.class})
public interface AccountMapper {
    AccountDto toDto(Account agency);
    List<AccountDto> toDto(List<Account> accounts);
    Account toModel(AccountDto agencyDto);


}
