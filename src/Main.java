import entity.Claim;
import entity.ClaimProcessManager;
import entity.ClaimProcessManagerImpl;
import service.ClaimService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClaimProcessManager claimProcessManager = new ClaimProcessManagerImpl();
        Scanner sc = new Scanner(System.in);
        List<Claim> claims = claimProcessManager.getAll();
        while (true) {
            printMenu();
            System.out.print("Choose: ");
            int choose = Integer.parseInt(sc.nextLine());
            switch (choose) {
                case 1:
                    claims = claimProcessManager.getAll();
                    claims.forEach(x -> System.out.println(x.printFormat()));
                    break;
                case 2:
                    System.out.print("Input id: ");
                    String id = sc.nextLine();
                    Claim claim = claimProcessManager.getOne(id);
                    if (claim == null) {
                        System.out.println("Claim not found!");
                    } else {
                        System.out.println(claim);
                    }
                    break;
                case 3:
                    Claim claim1 = ClaimService.inputClaim();
                    if (claim1 != null) {
                        System.out.println("Add successfully.");
                    } else {
                        System.out.println("Add unsuccessfully.");
                    }
                    claims = claimProcessManager.getAll();
                    break;
                case 4:
                    System.out.print("Input update claim id: ");
                    String updateId = sc.nextLine();
                    boolean found = false;
                    for (Claim claim3 : claims) {
                        if (claim3.getId().equals(updateId)) {
                            System.out.print("claimDate (yyyy-mm-dd): ");
                            Date claimDate = Date.valueOf(sc.nextLine());
                            System.out.print("claimAmount: ");
                            Integer claimAmount = Integer.parseInt(sc.nextLine());
                            System.out.print("status (New, Processing, Done): ");
                            String status = sc.nextLine();
                            System.out.print("receiverBankingInfo (Bank - Name - Info): ");
                            String receiverBankingInfo = sc.nextLine();
                            claim3.setClaimDate(claimDate);
                            claim3.setClaimAmount(claimAmount);
                            claim3.setStatus(status);
                            claim3.setReceiverBankingInfo(receiverBankingInfo);
                            System.out.println("Update successfully!");
                            found = true;
                        }
                    }
                    if (!found) {
                        System.out.println("Claim not found!");
                    }

                    ClaimService.saveClaims(claims);
                    break;
                case 5:
                    System.out.print("Input delete claim id: ");
                    String deleteId = sc.nextLine();
                    Claim claim3 = ClaimService.getOne(deleteId);
                    if (claim3 == null) {
                        System.out.println("Claim is not existed! Delete unsuccessfully!");
                    } else {
                        claims.removeIf(x -> x.getId().equals(deleteId));
                        System.out.println("Delete successfully!");
                    }
                    ClaimService.saveClaims(claims);
                    break;
                case 6:
                    System.out.println("GOODBYE!!!");
                    return;
            }
        }

    }

    public static void printMenu() {
        System.out.println("=============== MENU ================");
        System.out.println("1. Get all claims");
        System.out.println("2. Get claim");
        System.out.println("3. Add claim");
        System.out.println("4. Update claim");
        System.out.println("5. Delete claim");
        System.out.println("6. Exit");
        System.out.println("=====================================");
    }
}