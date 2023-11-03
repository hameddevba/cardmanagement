package com.starter.project.dto;

public class AccountDto {
    private Long id;

    private String accountNumber;

    private AgencyDto agency;

    private CustomerDto customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AgencyDto getAgency() {
        return agency;
    }

    public void setAgency(AgencyDto agency) {
        this.agency = agency;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}
