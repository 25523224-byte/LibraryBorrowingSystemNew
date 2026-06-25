package com.fad.LibrarySystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fad.LibrarySystem.model.Fine;
import com.fad.LibrarySystem.model.LibraryItem;
import com.fad.LibrarySystem.model.Member;
import com.fad.LibrarySystem.view.FineView;

public class FineController {

    private FineView fineView;
    private Scanner scanner;
    private List<Fine> fines;

    public FineController(Scanner scanner) {
        this.fineView = new FineView();
        this.scanner = scanner;
        this.fines = new ArrayList<>();
    }

    public void handleMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== Fine Menu =====");
            System.out.println("1. View All Fines");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> viewAllFines();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public Fine issueFine(String recordId,
                          Member member,
                          LibraryItem item,
                          int daysLate) {

        Fine fine = new Fine(
                recordId,
                member,
                item,
                daysLate
        );

        fines.add(fine);

        fineView.showFineIssued(
                member.getName(),
                fine.getFineAmount()
        );

        return fine;
    }

    private void viewAllFines() {
        fineView.showAllFines(fines);
    }

    public List<Fine> getFines() {
        return fines;
    }
}