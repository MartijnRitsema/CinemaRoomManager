package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Read the number of rows and seats
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeats = scanner.nextInt();

        // Initialize the seating arrangement
        char[][] seats = new char[numRows][numSeats];
        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeats; seat++) {
                seats[row][seat] = 'S';
            }
        }

        // Calculate the total number of seats
        int totalSeats = numRows * numSeats;
        int totalIncome = calculateTotalIncome(numRows, numSeats);

        while (true) {
            // Print the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            // Prompt the user for their choice
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Show the current seating arrangement
                    printSeatingArrangement(seats, numRows, numSeats);
                    break;
                case 2:
                    // Buy a ticket
                    buyTicket(scanner, seats, numRows, numSeats, totalSeats);
                    break;
                case 3:
                    // Show statistics
                    showStatistics(seats, totalSeats, totalIncome);
                    break;
                case 0:
                    // Exit the program
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void printSeatingArrangement(char[][] seats, int numRows, int numSeats) {
        System.out.println("\nCinema:");
        System.out.print("  ");
        for (int seat = 1; seat <= numSeats; seat++) {
            System.out.print(seat + " ");
        }
        System.out.println();
        for (int row = 1; row <= numRows; row++) {
            System.out.print(row + " ");
            for (int seat = 1; seat <= numSeats; seat++) {
                System.out.print(seats[row - 1][seat - 1] + " ");
            }
            System.out.println();
        }
    }

    public static void buyTicket(Scanner scanner, char[][] seats, int numRows, int numSeats, int totalSeats) {
        while (true) {
            // Prompt the user to enter a row number
            System.out.println("\nEnter a row number:");
            int chosenRow = scanner.nextInt();

            // Prompt the user to enter a seat number
            System.out.println("Enter a seat number in that row:");
            int chosenSeat = scanner.nextInt();

            if (chosenRow < 1 || chosenRow > numRows || chosenSeat < 1 || chosenSeat > numSeats) {
                System.out.println("Wrong input!");
                continue;
            }

            if (seats[chosenRow - 1][chosenSeat - 1] == 'B') {
                System.out.println("That ticket has already been purchased!");
            } else {
                // Mark the chosen seat with 'B'
                seats[chosenRow - 1][chosenSeat - 1] = 'B';

                // Calculate and display the ticket price
                int ticketPrice = calculateTicketPrice(numRows, numSeats, chosenRow);
                System.out.println("\nTicket price: $" + ticketPrice);
                break;
            }
        }
    }

    public static void showStatistics(char[][] seats, int totalSeats, int totalIncome) {
        int purchasedTickets = 0;
        int currentIncome = 0;
        int numRows = seats.length;
        int numSeats = seats[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int seat = 0; seat < numSeats; seat++) {
                if (seats[row][seat] == 'B') {
                    purchasedTickets++;
                    currentIncome += calculateTicketPrice(numRows, numSeats, row + 1);
                }
            }
        }

        double percentage = (double) purchasedTickets / totalSeats * 100;
        System.out.println("\nNumber of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static int calculateTicketPrice(int numRows, int numSeatsInRow, int rowNum) {
        int totalSeats = numRows * numSeatsInRow;
        int frontHalf = numRows / 2;
        int backHalf = numRows - frontHalf;

        if (totalSeats <= 60) {
            return 10;
        } else {
            if (rowNum <= frontHalf) {
                return 10;
            } else {
                return 8;
            }
        }
    }

    public static int calculateTotalIncome(int numRows, int numSeatsInRow) {
        int totalSeats = numRows * numSeatsInRow;
        int frontHalf = numRows / 2;
        int backHalf = numRows - frontHalf;

        if (totalSeats <= 60) {
            return totalSeats * 10;
        } else {
            return (frontHalf * numSeatsInRow * 10) + (backHalf * numSeatsInRow * 8);
        }
    }
}

