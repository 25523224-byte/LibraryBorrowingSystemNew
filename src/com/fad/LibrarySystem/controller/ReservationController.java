package com.fad.LibrarySystem.controller;

import java.util.Scanner;

import com.fad.LibrarySystem.model.Librarian;
import com.fad.LibrarySystem.model.LibraryItem;
import com.fad.LibrarySystem.model.Member;
import com.fad.LibrarySystem.model.Reservation;

public class ReservationController {

    private Librarian librarian;
    private Scanner scanner;

    public ReservationController(Librarian librarian, Scanner scanner) {
        this.librarian = librarian;
        this.scanner = scanner;
    }

    public void handleMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n===== Reservation Menu =====");
            System.out.println("1. Create Reservation");
            System.out.println("2. View Reservations");
            System.out.println("0. Back");
            System.out.print("Choose an option: ");

            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1 -> createReservation();
                case 2 -> viewReservations();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void createReservation() {

        System.out.print("Member ID : ");
        String memberId = scanner.nextLine().trim();

        System.out.print("Item ID   : ");
        String itemId = scanner.nextLine().trim();

        if (memberId.isEmpty() || itemId.isEmpty()) {
            System.out.println("Member ID and Item ID cannot be empty.");
            return;
        }

        Member member = librarian.findMemberById(memberId);
        LibraryItem item = librarian.findItemById(itemId);

        if (member == null) {
            System.out.println("Member not found.");
            return;
        }

        if (item == null) {
            System.out.println("Item not found.");
            return;
        }

        try {
            Reservation reservation =
                    librarian.addReservation(member, item);

            if (reservation != null) {
                System.out.println("Reservation created successfully.");
                System.out.println(reservation.getInfo());
            } else {
                System.out.println("Failed to create reservation.");
            }

        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewReservations() {

        int count = librarian.getReservationCount();

        if (count == 0) {
            System.out.println("No reservations found.");
            return;
        }

        System.out.println("===== Reservation List =====");

        Reservation[] reservations =
                librarian.getReservations();

        for (int i = 0; i < count; i++) {
            System.out.println(
                    reservations[i].getInfo()
            );
        }
    }
}