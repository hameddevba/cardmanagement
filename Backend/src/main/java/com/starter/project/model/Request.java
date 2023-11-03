package com.starter.project.model;

import com.starter.project.model.enums.LanguageEnum;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_type_id")
    private CardType cardType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    private String observation;
    @Temporal(TemporalType.DATE)
    private Date creationDate;
    private String rejectionReason;

    private BigDecimal cardLimit;
    private String nameOnCard;
    private LanguageEnum smsLang;
    @Temporal(TemporalType.DATE)
    private Date smsSentDate;
    private Integer renewMonth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
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
