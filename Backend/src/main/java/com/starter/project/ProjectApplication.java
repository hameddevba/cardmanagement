package com.starter.project;

import com.starter.project.dao.CardTypeDao;
import com.starter.project.dao.CustomerDao;
import com.starter.project.model.*;
import com.starter.project.model.enums.LanguageEnum;
import com.starter.project.service.AccountService;
import com.starter.project.service.AgencyService;
import com.starter.project.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {
    @Autowired
    RequestService requestService;

    @Autowired
    AccountService accountService;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    AgencyService agencyService;

    @Autowired
    CardTypeDao cardTypeDao;

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        createRequest();

    }

    private void createRequest() {
        var cardType = new CardType();
        cardType.setCode("code");
        cardType.setLabel("label");
        cardTypeDao.save(cardType);
        Agency agency = new Agency();
        agency.setCode("code");
        agency.setLabel("label");
        agencyService.save(agency);
        Customer customer = new Customer();
        customer.setBirthDate(new Date());
        customer.setAddress("120 avenue roger salengro");
        customer.setFirstName("first name");
        customer.setLastName("last name");
        customer.setNni("nni");
        customer.setTitle("title");
        customer.setPhoneNumber(11L);
        customerDao.save(customer);
        Account account = new Account();
        account.setAccountNumber("accountNumber");
        account.setAgency(agency);
        account.setCustomer(customer);
        accountService.save(account);
        Request request = new Request();
        request.setAccount(account);
        request.setCreationDate(new Date());
        request.setAgency(agency);
        request.setNameOnCard("name on card");
        request.setCardType(cardType);
        request.setSmsLang(LanguageEnum.FR);
        requestService.save(request);
    }

}
