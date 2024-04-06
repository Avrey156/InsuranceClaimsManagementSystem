package service;

import entity.Customer;
import entity.InsuranceCard;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InsuranceCardService {
    public static List<InsuranceCard> getAll() {
        List<InsuranceCard> insuranceCards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./database/InsuranceCard.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String id = parts[0];
                    String cardHolder = parts[1];
                    Date expirationDate = Date.valueOf(parts[2]);
                    insuranceCards.add(new InsuranceCard(id, cardHolder, expirationDate));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return insuranceCards;
    }


    public static boolean isCardNumberExist(String cardNumber) {
        return getAll().stream().anyMatch(x -> x.getCardNumber().equals(cardNumber));
    }
    public static InsuranceCard getOne(String cardNumber) {
        return getAll().stream().filter(x->x.getCardNumber().equals(cardNumber)).findFirst().orElse(null);
    }

    public static InsuranceCard inputInsuranceCard(String cardNumber) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter cardHolder (c - 7 numbers): ");
        String cardHolder = sc.nextLine();
        if (!CustomerService.isExistCardHolder(cardHolder)) {
            Customer customer = CustomerService.inputCustomer(cardHolder);
        }
        System.out.print("Enter expirationDate (yyyy-mm-dd): ");
        Date expirationDate = Date.valueOf(sc.nextLine());
        InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolder, expirationDate);
        saveInsuranceCardToFile(insuranceCard);
        return insuranceCard;
    }

    private static void saveInsuranceCardToFile(InsuranceCard insuranceCard) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./database/InsuranceCard.txt", true))) {
            writer.newLine();
            writer.write(insuranceCard.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static InsuranceCard getInsuranceCard(String cardNumber){
        return InsuranceCardService.getOne(cardNumber);
    }
}
