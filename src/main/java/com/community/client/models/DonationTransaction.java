package com.community.client.models;
import javax.persistence.*;
//creating the columns for the 'tansaction_table'

@Entity
@Table(name="transaction_table")
public class DonationTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long id;

    @Column(name="donor_name", nullable = false)
    private String donorName;

    @Column(name="donor_email", nullable = false)
    private String donorEmail;

    @Column(name="amount", nullable = false, precision = 2)
    private String amount;

    @Column(name = "card_number", nullable = false)
    private String creditCard;

    @Column(name = "date", nullable = false)
    private String date;

    @Column(name = "project_id", nullable = false)
    private String projectId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name="card_cvc", nullable = false)
    private String cardCvc;

    @Column(name="card_year", nullable = false)
    private String cardYear;

    @Column(name="card_month", nullable = false)
    private String cardMonth;


    //Setting up the Constructor
    public DonationTransaction() {
    }
    public DonationTransaction(Long id, String donorName, String donorEmail, String amount, String creditCard, String date, String projectId, String userId, String cardCvc, String cardYear, String cardMonth) {
        this.id = id;
        this.donorName = donorName;
        this.donorEmail = donorEmail;
        this.amount = amount;
        this.creditCard = creditCard;
        this.date = date;
        this.projectId = projectId;
        this.userId = userId;
        this.cardCvc = cardCvc;
        this.cardYear = cardYear;
        this.cardMonth = cardMonth;
    }


    //Setting up the getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCardCvc() {
        return cardCvc;
    }

    public void setCardCvc(String cardCvc) {
        this.cardCvc = cardCvc;
    }

    public String getCardYear() {
        return cardYear;
    }

    public void setCardYear(String cardYear) {
        this.cardYear = cardYear;
    }

    public String getCardMonth() {
        return cardMonth;
    }

    public void setCardMonth(String cardMonth) {
        this.cardMonth = cardMonth;
    }
}
