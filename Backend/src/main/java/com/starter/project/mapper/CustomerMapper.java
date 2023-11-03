package com.starter.project.mapper;

import com.starter.project.dto.CustomerDto;
import com.starter.project.model.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    List<CustomerDto> toDto(List<Customer> customers);
    Customer toModel(CustomerDto customerDto);


}
