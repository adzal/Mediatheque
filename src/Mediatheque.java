import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Mediatheque {
	private Scanner scanner = new Scanner(System.in);
	private String name;
	private ArrayList<Media> availableMedia = new ArrayList<Media>();
	private ArrayList<Media> borrowedMedia = new ArrayList<Media>();

	public Mediatheque(String name) {
		this.setName(name);
		getTestMedia();
	}

	@Override
	public String toString() {
		String s = "In " + name + " we have the following to borrow";
		for (Media media : availableMedia) {
			s += "\n" + media;
		}
		return s;
	}

	public void addMedia(Media media) {
		availableMedia.add(media);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * faked database
	 */
	private void getTestMedia() {
		// Add a mix of Dvds, books, comicbooks and magazines
		addMedia(new Dvd("The Godfather", 175));
		addMedia(new Dvd("The Godfather II", 202));
		addMedia(new Dvd("The Godfather III", 158));
		addMedia(new Dvd("Star Wars", 121));
		addMedia(new Dvd("The Empire Strikes Back", 124));
		addMedia(new Dvd("Return of the Jedi", 131));

		addMedia(new Book("Harry Potter", 300));
		addMedia(new Book("Harry Potter II", 311));

		addMedia(new ComicBook("2000 AD", 1, 30));
		addMedia(new ComicBook("The Walking Dead", 2, 42));
		addMedia(new ComicBook("The Amazing Spider-Man", 1, 33));

		addMedia(new Magazine("Time", "March", 2020, 42));
		addMedia(new Magazine("Time", "January", 2021, 40));
	}

	/**
	 * Here is the main menu that will ask the user to enter a type of media to
	 * search for.
	 */
	public void mainMenu() {
		char choice = ' ';
		while (choice != 'Q') {
			System.out.println("\nWelcome to " + name + ".");

			showBorrowedMedia();

			System.out.println("(L)ist all");
			System.out.println("(D)vds");
			System.out.println("(B)ooks");
			System.out.println("(C)omicbooks");
			System.out.println("(M)agazine");
			System.out.println();
			System.out.println("(S)ave media list");
			System.out.println("(R)estore media list");
			System.out.println();
			System.out.println("(Q)uit");
			System.out.println();
			System.out.print("Choice>");

			// Input choice
			String line = scanner.nextLine().toUpperCase();
			choice = line.charAt(0);
			System.out.println();
			
			switch (choice) {
			case 'L' -> mediaMenu();
			case 'D' -> mediaMenu(Dvd.class);
			case 'B' -> mediaMenu(Book.class);
			case 'C' -> mediaMenu(ComicBook.class);
			case 'M' -> mediaMenu(Magazine.class);
			case 'S' -> backup();
			case 'R' -> restore();
			case 'Q' -> System.out.println("Thank you for your visit to " + name + ".\nCome back soon :)");
			default -> System.out.println("Invalid request!");
			}
		}
	}

	private void backup() {
		try {
			MediaSerializer m = new MediaSerializer();
			m.backupMedia("availablemedia.json", availableMedia);
			m.backupMedia("borrowedMedia.json", borrowedMedia);

			System.out.printf("Data is serialized");
		} catch (IOException e) {
			System.out.printf("Serialization failed, err:" + e.getMessage());
		}
	}

	private void restore() {
		try {
			MediaSerializer m = new MediaSerializer();
			availableMedia = m.restoreMedia("availablemedia.json");
			borrowedMedia = m.restoreMedia("borrowedMedia.json");
		} catch (IOException i) {
			System.out.printf("Serialization failed, err:" + i.getMessage());

		} catch (ClassNotFoundException c) {
			System.out.printf("Serialization failed, err:" + c.getMessage());
		}
	}

	private void showBorrowedMedia() {
		if (borrowedMedia.size() > 0) {
			System.out.println("Here is what you've selected so far:");
			for (Media media : borrowedMedia) {
				System.out.println(" ".repeat(4) + media.toString());
			}
		}
	}

	/**
	 * For all media pass null.
	 */
	private void mediaMenu() {
		mediaMenu(null);
	}

	/**
	 * This accepts as parameter the type of class to process if null displays all
	 * items. The <T> is for the generic type passed.
	 * 
	 * @param <T>
	 * @param type
	 */
	private <T> void mediaMenu(T type) {
		int index = 0;

		// build and display a list of media for this type passed
		System.out.println("What do you want to borrow?");
		ArrayList<Media> toBorrow = new ArrayList<Media>();
		for (Media media : availableMedia) {
			if (type == null || media.getClass().equals(type)) {
				toBorrow.add(media);
				System.out.println("(" + index++ + ") " + media.display());
			}
		}
		System.out.println("\n(Q)uit");

		// Get the user's input to add to list of borrowed items
		char choice = ' ';
		while (choice != 'Q') {
			System.out.println("Enter the number you want to borrow, or 'Q' to exit.");
			// Input choice
			String line = scanner.nextLine().toUpperCase();

			choice = line.charAt(0);
			if (choice != 'G') {
				try {
					// Convert from string into an integer then get that element
					int number = Integer.parseInt(line);
					if (number <= index) {
						Media selectedMedia = toBorrow.get(number);
						borrow(selectedMedia);
					} else {
						System.out.println(number + " does not exist!");
					}
				} catch (NumberFormatException ex) {
					System.out.println("Invalid choice!");
				}
			}
		}
	}

	/**
	 * If there still in stock, update the list of borrowed media with the
	 * selection, decrease the number available.
	 * 
	 * @param selectedMedia
	 */
	private void borrow(Media selectedMedia) {
		if (selectedMedia.noAvailable == 0) {
			System.out.println(selectedMedia.title + " is out of stock :(");
		} else {
			selectedMedia.noAvailable--;
			borrowedMedia.add(selectedMedia);
			System.out.println(selectedMedia.toString());
		}
	}
}
