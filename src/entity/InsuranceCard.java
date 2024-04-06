package entity;

import jdk.jfr.Description;

import java.sql.Date;

public class InsuranceCard {
    @Description("10 digits")
    private String cardNumber;

    private String cardHolder;

    private Date expirationDate;

    public InsuranceCard(String cardNumber, String cardHolder, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return cardNumber + "," + cardHolder + "," + expirationDate;
    }
}
