package benn.mcgregor;


import java.util.Scanner;
import java.io.File;

public class BookReviewDriver {
	static Scanner in = new Scanner(System.in);
	static BookShelf wishList = new BookShelf();
	static BookShelf purchaseList = new BookShelf();
	static String yn = "";
	
	/**
	 * Runs the application.
	 * @param args
	 */
	public static void main(String[] args) {
		String menu = "1 - Display Wish List \n2 - Add a book to the Wish List" + 
				"\n3 - Review a book on the Wish List " +
				"\n4 - Read a book's reviews" +
				"\n5 - Purchase a book from the Wish List" +
				"\n6 - Display purchased Books \n7 - Quit";
		int choice = 0;
		boolean exit = false;
		
		System.out.println("Do you have a book wish list? (y/n)");
		yn = in.nextLine();
		while (!(yn.startsWith("y") || yn.startsWith("n"))) {
			System.out.println("Please answer with yes (y) or no (n).");
			yn = in.next().toLowerCase();
		}
		
		while (yn.startsWith("y") && wishList.getNumBooks()==0) {
			loadWishList();
		}
		
		while (!exit) {
			System.out.println(menu);
			
			
			while (!in.hasNextInt()) {
				in.nextLine();
				System.out.println("Please give a valid number!");
			}
			choice = Integer.parseInt(in.nextLine());
			
			switch(choice) {
			case 1:
				printWishList();
				break;
			case 2:
				addBookToWishList();
				break;
			case 3:
				addReview();
				break;
			case 4:
				getAllReviews();
				break;
			case 5:
				purchaseBook();
				break;
			case 6:
				displayPurchasedBooks();
				break;
			case 7:
				exit = true;
				System.out.println("Bye!");
				break;
			}
		}
	}
	
	
	private static void loadWishList()  {
		System.out.println("What is the filename of your wishlist (Must be csv)?"
				+ " Type n if you don't actually have a file.");
		String file = in.nextLine();
		
		if (file.toLowerCase().equals("n")) {
			yn = file.toLowerCase();;
			return;
		}
		
		try {
			File wishListCSV = new File(file);
			Scanner fileinput = new Scanner(wishListCSV);
			
			fileinput.nextLine(); //skip first line to avoid generic text
			while (fileinput.hasNextLine()) {
				String[] line = fileinput.nextLine().split(",");
				Book temp = new Book(removeQuotes(line[1]),
						removeQuotes(line[2]), Long.parseLong(removeQuotes(line[3])));
				wishList.add(temp);
			}
			fileinput.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	private static String removeQuotes(String s) {
		return s.substring(1,s.length()-1);
	}


	private static void printWishList() {
		System.out.printf("\nYou have %d books in your wish list:", wishList.getNumBooks());
		System.out.println(wishList);
	}
	
	private static void addBookToWishList() {
		String title = "";
		String author = "";
		long isbn = 0;
		
		System.out.println("What is the title of the book?");
		title = in.nextLine();
		System.out.println("What is the author of the book?");
		author = in.nextLine();
		System.out.println("What is the ISBN of the book?");
		while (!in.hasNextLong()) {
			in.nextLine();
			System.out.println("Please give a valid number!");
		}
		isbn = Long.parseLong(in.nextLine());
		
		Book temp = new Book(title, author, isbn);
		wishList.add(temp);
		System.out.println("Book added! Here it is: ");
		System.out.println(temp);
		
	}
	
	
	private static void addReview() {
		String review = "";
		String name = "";
		float rating = 0;
		boolean done = false;
		
		int index = robustISBN("What is the ISBN of the book you would like to review?");
		
		System.out.println("What is your review?");
		review = in.nextLine();
		System.out.println("What is your name?");
		name = in.nextLine();
		System.out.println("What is your rating (out of 5)");
		while (!done) {
			if (in.hasNextFloat()) {
				rating = Float.parseFloat(in.nextLine());
				if (rating <= 5 && rating >= 0) {
					done = true;
				} else {
					System.out.println("Please give a valid number between 0 and 5!");
				}
			} else {
				in.nextLine();
				System.out.println("Please give a number!");
			}
		}
		
		Review r = new Review(name, review, rating);
		wishList.getBooks().get(index).addReview(r);
		System.out.println("Review added! Here it is: ");
		System.out.println(r);
	}
	
	
	private static void getAllReviews() {
		int index = robustISBN("What is the ISBN of the book you would read reviews for?");
		System.out.println(wishList.getBooks().get(index).getReviews());
	}
	
	
	private static void purchaseBook() {
		Book purchase;
		int index = robustISBN("What is the ISBN of the book you would like to purchase?");
		
		purchase = wishList.getBooks().get(index);
		purchaseList.add(purchase);
		wishList.remove(purchase);
		System.out.printf("\n\"%s\" added to your list of purchased books! Your current purchased books:", purchase.getTitle());
		System.out.println(purchaseList);
	}
	
	
	private static void displayPurchasedBooks() {
		System.out.printf("\nYou have %d purchased books:", purchaseList.getNumBooks());
		System.out.println(purchaseList);
	}
	
	
	private static int robustISBN(String prompt) {
		boolean done = false;
		long isbn = 0;
		
		System.out.println(prompt);
		while (!done) { 
			if (in.hasNextLong()) {
				isbn = Long.parseLong(in.nextLine());
				if (wishList.hasBookWithISBN(isbn)) {
					return wishList.getBookIndex(isbn);
				} else {
					System.out.println("There is no book with that ISBN, please try again: ");
				}
			} else {
				in.nextLine();
				System.out.println("Please give a valid ISBN: ");
			}
		}
		return -1;
	}

}
