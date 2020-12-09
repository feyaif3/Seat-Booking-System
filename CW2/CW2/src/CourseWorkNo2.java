import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class CourseWorkNo2 {

	static ArrayList<Seats> reservations = new ArrayList<Seats>(); // stores all the booking from the file into the
																	// array

	static ArrayList<String> userResultsArray = new ArrayList<String>();

	static Scanner input = new Scanner(System.in); // declaring the scanner, which then accepts the user input

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		makingReservations();
		// This are all the variables
		String response;
		String answer;
		String reserveSeat;
		String cancelSeat;
		String viewSeatReservation;
		String quit;

		Scanner reply = new Scanner(System.in);
		// This is the menu which prints out
		System.out.println("\n--MAIN MENU --");
		System.out.println("1 -Reserve Seat");
		System.out.println("2 -Cancel Seat");
		System.out.println("3 -View Seat Reservation");
		System.out.println("Q - Quit\n");
		System.out.print("Pick : ");
		do {
			response = reply.next(); // stores the user input into the response variable

			// switch case will print out the message from whatever option has been selected
			// from the user
			switch (response) {
			case "1":
				System.out.println("Seat reservation has been selected");
				seatReservation();
				break;
			case "2":
				System.out.println("Seat cancelation has been selected");
				break;
			case "3":
				System.out.println("Viewing seat reservation has been selected");
				viewReservations();
				break;
			case "Q":
				System.out.println("Goodbye");
			case "q":
				System.out.println("Goodbye");
				break;
			default:
				System.out.println("Options above must be selected");
			}
		} while (!response.equalsIgnoreCase("Q"));
	}

	// This is my makingReservation method
	public static void makingReservations() throws FileNotFoundException {
		String fileName = "C:\\data\\seats.txt"; // Storing file location so it can be read
		FileReader file = new FileReader(fileName);
		Scanner read = new Scanner(file);
		Seats singleReservation;
		String seats;

		while (read.hasNext()) { // This reads each individual line from the file, then it will store it in
									// tokens
			singleReservation = new Seats(read.next(), read.next(), read.nextBoolean(), read.nextBoolean(),
					read.nextBoolean(), read.nextDouble(), read.next());
			reservations.add(singleReservation);

		}
		read.close();

	}

	public static void seatReservation() {
		String response = null;

		System.out.println("Display available");

		for (Seats booking : reservations) {

			if (booking.eMail.equals("free")) {
				System.out.format("%s, %s, £%.2f\n", booking.seatNum, booking.seatClass, booking.seatPrice);
				// This is how the text will be	formatted
			}
		}
			Seats seat = new Seats(null, null, null, null, null, null, null);

			boolean classChoiceLoop = true;

			while (classChoiceLoop) {

				System.out.println("Do you want a Standard seat or First Class seat?");
				response = input.nextLine();

				if (response.equalsIgnoreCase("Standard")) {
					response = "STD";
					classChoiceLoop = false;
				}

				else if (response.equalsIgnoreCase("First")) {
					response = "1ST";
					classChoiceLoop = false;

				}

				else {
					System.out.println("Invalid Input");
					// This will ask the customer what type of seat class they want then anything
					// which isn't what is asked for will prompt to an error and asked to be typed
					// again
				}

				seat.seatClass = response; // This puts their answer into the seat class
			}

			Boolean windowChoiceLoop = true;

			while (windowChoiceLoop) {

				System.out.println("Do you want a Window seat?\n A:yes\n B:no");
				response = input.nextLine();

				if (response.equalsIgnoreCase("A")) {
					response = "true";
					windowChoiceLoop = false;
				}

				else if (response.equalsIgnoreCase("B")) {
					response = "false";
					windowChoiceLoop = false;
				}

				else {
					System.out.println("Invalid Input");
				}
				seat.isWindow = Boolean.parseBoolean(response); // This takes the variable and makes it into boolean
			}

			Boolean aisleChoiceLoop = true;
			while (aisleChoiceLoop) {

				System.out.println("Do you want a Aisle seat?\n A:yes\n B:no");
				response = input.nextLine();

				if (response.equalsIgnoreCase("A")) {
					response = "true";
					aisleChoiceLoop = false;
				}

				else if (response.equalsIgnoreCase("B")) {
					response = "false";
					aisleChoiceLoop = false;
				}

				else {
					System.out.println("Invalid Input");
				}

				seat.isAisle = Boolean.parseBoolean(response);
			}

			Boolean tableChoiceLoop = true;
			while (tableChoiceLoop) {

				System.out.println("Do you want a seat with a Table?\n A:yes\n B:no");
				response = input.nextLine();

				if (response.equalsIgnoreCase("A")) {
					response = "true";
					tableChoiceLoop = false;
				}

				else if (response.equalsIgnoreCase("B")) {
					response = "false";
					tableChoiceLoop = false;
				}

				else {
					System.out.println("Invalid Input");
				}
				seat.isWindow = Boolean.parseBoolean(response);
			}

			boolean seatPriceLoop = false;
			while (!seatPriceLoop) {

				System.out.println("Whats is your maximun spend?"); // This is for the customer to enter their ticket
																	// price
																	// range
				response = input.next();
				double seatPriceCheck = 0;

				try {
					seatPriceCheck = Double.parseDouble(response);
				} catch (Exception e) {
					System.out.println("Seat Price must be a number between 0-80");
					continue;
				}

				if (seatPriceCheck > 0 || seatPriceCheck < 80) { // anything between 0-80 will be a valid price anything
																	// else entered will cause the system to ask again
					seatPriceLoop = true;
				}
			}

			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).seatClass.equals(seat.seatClass)
						&& reservations.get(i).isWindow == (seat.isWindow)
						&& reservations.get(i).isAisle == (seat.isAisle)
						&& reservations.get(i).isTable == (seat.isTable)
						&& reservations.get(i).seatPrice == seat.seatPrice) {
					System.out.println(reservations.get(i));
				}
			}
			System.out.println("Enter Seat Number for the booking you would like to book");
			response = input.next();

			for (int i = 0; i < reservations.size(); i++) {
				if (reservations.get(i).seatNum.contentEquals(response)) {

					System.out.println("Enter eMail to reserve this booking");
					reservations.get(i).eMail = input.next();

					System.out.println("Boking reserved:\n" + reservations.get(i));
				}
			}
		}

	

	public static void seatCancel() {
		String cancelSeatNo;
		String confirmCancel;

		System.out.println("Which seat would you want to cancel?");
		cancelSeatNo = input.next();
		for (int j = 0; j < reservations.size(); j++) {
			if (reservations.get(j).seatNum.equalsIgnoreCase(cancelSeatNo)) {
				if (reservations.get(j).eMail.equalsIgnoreCase("free")) {
					System.out.println("This seat has not been reserved");
				} else {
					System.out.println("Are you sure you want to cancel the seat? \n A:yes \n B:no");
					confirmCancel = input.next();
					if (confirmCancel.equalsIgnoreCase("yes")) {
						reservations.get(j).eMail = "free";
						System.out.println("Seat has been cancelled");
					} else {
						System.out.println("The seat will not be cancelled");
					}

				}
			}
		}

	}

	public static void viewReservations() {

		for (Seats booking : reservations) {

			if (!booking.eMail.equals("free")) {
				System.out.format("%s, %s, £%.2f %s\n", booking.seatNum, booking.seatClass, booking.seatPrice,
						booking.eMail); // This is how the text will be formatted
			}

		}

	}

}
