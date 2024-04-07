package entity;

/**
 * @author <Nguyen Ngoc Minh Thu - S3941327>
 */

import jdk.jfr.Description;
import service.CustomerService;
import service.InsuranceCardService;
import utils.StringUtils;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Claim {
    @Description("f - 10 numbers")
    private String id;
    private Date claimDate;
    private String cardNumber;
    private Date examDate;
    @Description("ClaimId_CardNumber_DocumentName.pdf")
    private List<String> documents;

    private Integer claimAmount;
    @Description("New, Processing, Done")
    private String status;
    @Description("Bank - Name - Info")
    private String receiverBankingInfo;

    public Claim(){

    }
    public Claim(String id, Date claimDate, String cardNumber, Date examDate, List<String> documents, Integer claimAmount, String status, String receiverBankingInfo) {
        this.id = id;
        this.claimDate = claimDate;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.claimAmount = claimAmount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public Integer getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Integer claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public void setReceiverBankingInfo(String receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    @Override
    public String toString() {
        return id + "," + claimDate + "," + cardNumber + "," + examDate + "," + documents + "," + claimAmount + "," + status + "," + receiverBankingInfo;
    }
    public String printFormat(){
        return id + "," + claimDate + "," + cardNumber + "-" + CustomerService.getOne(InsuranceCardService.getInsuranceCard(cardNumber).getCardHolder()).getFullName() + "," + examDate + "," + documents + "," + claimAmount + "," + status + "," + receiverBankingInfo;
    }

    public String fileFormat() {
        return id + "," + claimDate + "," + cardNumber + "," + examDate + "," + StringUtils.splitByDash(documents) + "," + claimAmount + "," + status + "," + receiverBankingInfo;
    }
}
