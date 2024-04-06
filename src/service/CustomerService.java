package service;

import entity.Customer;
import entity.Dependent;
import entity.PolicyHolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerService {
    public static List<Dependent> getAllDependents() {
        List<Dependent> dependents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./database/Dependent.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length == 2) {
                    String id = parts[0];
                    String name = parts[1];
                    dependents.add(new Dependent(id, name));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dependents;
    }

    public static List<PolicyHolder> getAllPolicyHolders() {
        List<PolicyHolder> policyHolders = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("./database/PolicyHolder.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] parts = line.split(",");
                if(parts.length == 3) {
                    String id = parts[0];
                    String name = parts[1];
                    List<String> dependentIds = new ArrayList<>();
                    if (!parts[2].trim().equals("[]")) {
                        String[] notesArray = parts[2].trim().replaceAll("[\\[\\]]", "").split(";");
                        for (String note : notesArray) {
                            dependentIds.add(note.trim());
                        }
                    }
                    policyHolders.add(new PolicyHolder(id, name, dependentIds));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return policyHolders;
    }

    public static List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();
        customers.addAll(getAllDependents());
        customers.addAll(getAllPolicyHolders());
        return customers;
    }

    public static boolean isExistCardHolder(String cardHolder) {
        return getAll().stream().anyMatch(x -> x.getId().equals(cardHolder));
    }

    public static Customer inputCustomer(String cardHolder) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter fullName: ");
        String fullName = sc.nextLine();
        System.out.println("Type of customer: ");
        System.out.println("1) Dependent");
        System.out.println("2) PolicyHolder");
        System.out.print("Type: ");
        Integer type = Integer.parseInt(sc.nextLine());
        Customer customer = null;
        switch (type){
            case 1:
                Dependent dependent = new Dependent(cardHolder, fullName);
                saveCustomerToFile(dependent);
                customer = dependent;
                break;
            case 2:
                List<String> dependentIds = new ArrayList<>();
                System.out.print("Number of dependentIds: ");
                Integer number = Integer.parseInt(sc.nextLine());
                for(int i =0; i<number; i++){
                    System.out.println(i + ": ");
                    System.out.print("Enter dependentId (c-7digits): ");
                    String dependentId = sc.nextLine();
                    if(!isExistCardHolder(dependentId)){
                        System.out.print("Enter fullName: ");
                        String fullname = sc.nextLine();
                        Dependent dependent1 = new Dependent(dependentId, fullname);
                        saveCustomerToFile(dependent1);
                    }
                    dependentIds.add(dependentId);
                }
                PolicyHolder policyHolder = new PolicyHolder(cardHolder, fullName, dependentIds);
                saveCustomerToFile(policyHolder);
                customer = policyHolder;
                break;
        }
        return customer;
    }

    private static void saveCustomerToFile(Customer customer) {
        if (customer instanceof Dependent){
            Dependent dependent = (Dependent) customer;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./database/Dependent.txt", true))) {
                writer.newLine();
                writer.write(dependent.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if(customer instanceof PolicyHolder){
            PolicyHolder policyHolder = (PolicyHolder) customer;
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("./database/PolicyHolder.txt", true))) {
                writer.newLine();
                writer.write(policyHolder.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Customer getOne(String customerID){
        return getAll().stream().filter(x->x.getId().equals(customerID)).findFirst().orElse(null);
    }
}
