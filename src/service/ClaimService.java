package service;

import entity.Claim;
import entity.InsuranceCard;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClaimService {
    public static List<Claim> getAll() {
        List<Claim> claims = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./database/Claim.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 8) {
                    String id = parts[0];
                    Date claimDate = Date.valueOf(parts[1]);
                    String cardNumber = parts[2];
                    Date examDate = Date.valueOf(parts[3]);
                    List<String> documents = new ArrayList<>();
                    if (!parts[4].trim().equals("[]")) {
                        String[] notesArray = parts[4].trim().replaceAll("[\\[\\]]", "").split(";");
                        for (String note : notesArray) {
                            documents.add(note.trim());
                        }
                    }
                    Integer claimAmount = Integer.parseInt(parts[5]);
                    String status = parts[6];
                    String receiverBankingInfo = parts[7];
                    Claim claim = new Claim(id, claimDate, cardNumber, examDate, documents, claimAmount, status, receiverBankingInfo);
                    claims.add(claim);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return claims;
    }

    public static Claim getOne(String claimId) {
        return getAll().stream().filter(x->x.getId().equals(claimId)).findFirst().orElse(null);
    }

    public static Claim inputClaim() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id (f - 10 numbers): ");
        String id = sc.nextLine();
        System.out.print("Enter claimDate (yyyy-mm-dd): ");
        Date claimDate = Date.valueOf(sc.nextLine());
        System.out.print("Enter cardNumber (10 digits): ");
        String cardNumber = sc.nextLine();
        if(!InsuranceCardService.isCardNumberExist(cardNumber)){
            InsuranceCard insuranceCard = InsuranceCardService.inputInsuranceCard(cardNumber);
        }
        System.out.print("Enter examDate (yyyy-mm-dd): ");
        Date examDate = Date.valueOf(sc.nextLine());
        System.out.print("How many documents do you want to input? ");
        Integer documentsNumber = Integer.parseInt(sc.nextLine());
        List<String> documents = new ArrayList<>();
        if(documentsNumber > 0) {
            System.out.println("Input document name (ClaimId_CardNumber_DocumentName.pdf): ");
            for (int i = 0; i < documentsNumber; i++) {
                System.out.print(i + ": ");
                documents.add(sc.nextLine());
            }
        }
        System.out.print("Enter claimAmount: ");
        Integer claimAmount = Integer.parseInt(sc.nextLine());
        System.out.print("Input status (New, Processing, Done): ");
        String status = sc.nextLine();
        System.out.print("Input receiverBankingInfo (Bank - Name - Info): ");
        String receiverBankingInfo = sc.nextLine();
        Claim claim = new Claim(id, claimDate, cardNumber, examDate, documents, claimAmount, status, receiverBankingInfo);
        saveClaimToFile(claim);
        return claim;
    }

    private static void saveClaimToFile(Claim claim) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./database/Claim.txt", true))) {
            writer.newLine();
            writer.write(claim.fileFormat());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveClaims(List<Claim> claims) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./database/Claim.txt"))) {
            for(Claim claim: claims){
                writer.write(claim.fileFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
