package com.starter.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.starter.project.model.enums.LanguageEnum;

import java.math.BigDecimal;
import java.util.Date;

public class RequestDto {
    private Long id;
    private AccountDto account;
    private CardTypeDto cardType;
    private CardTypeDto card;
    private String observation;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date creationDate;
    private String rejectionReason;
    private BigDecimal cardLimit;
    private String nameOnCard;
    private LanguageEnum smsLang;
    private Date smsSentDate;
    private Integer renewMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public CardTypeDto getCardType() {
        return cardType;
    }

    public void setCardType(CardTypeDto cardType) {
        this.cardType = cardType;
    }

    public CardTypeDto getCard() {
        return card;
    }

    public void setCard(CardTypeDto card) {
        this.card = card;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public BigDecimal getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(BigDecimal cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    public LanguageEnum getSmsLang() {
        return smsLang;
    }

    public void setSmsLang(LanguageEnum smsLang) {
        this.smsLang = smsLang;
    }

    public Date getSmsSentDate() {
        return smsSentDate;
    }

    public void setSmsSentDate(Date smsSentDate) {
        this.smsSentDate = smsSentDate;
    }

    public Integer getRenewMonth() {
        return renewMonth;
    }

    public void setRenewMonth(Integer renewMonth) {
        this.renewMonth = renewMonth;
    }
}
