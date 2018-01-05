package benn.mcgregor;

/**
 * BookShelf defined by an ArrayList of Book objects
 * ordered from least to greatest ISBN
 * @author benn.mcgregor
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Collections;

public class BookShelf { //data structure for wishlist and purchased books list.
	private ArrayList<Book> books;
	
	/**
	 * Constructor that instantiates ArrayList books.
	 */
	BookShelf() {
		books = new ArrayList<Book>();
	}
	
	/**
	 * Mutator that adds a Book to ArrayList books and
	 * resorts the books by ISBN.
	 * @param Book b
	 */
	public void add(Book b) {
		books.add(b);
		Collections.sort(books);
	}
	
	/**
	 * Mutator that removes a Book from ArrayList books.
	 * @param Book b
	 */
	public void remove(Book b) {
		books.remove(b);
	}
	
	/**
	 * Accessor that gets ArrayList books.
	 * @return ArrayList<Book> books
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}
	
	/**
	 * Accessor that gets the number of books on the bookshelf.
	 * @return int the number of Book instances in ArrayList books.
	 */
	public int getNumBooks() {
		return books.size();
	}
	
	/**
	 * @return the toString output of each book in the bookshelf, line-broken.
	 */
	@Override
	public String toString() {
		String rtrn = "\n";
		for (Book i : books) {
			rtrn += i.toString() + "\n";
		}
		return rtrn;
	}
	
	/**
	 * Checks if the BookShelf contains a book with specific ISBN.
	 * @param long isbn
	 * @return whether the BookShelf contains a book with this isbn.
	 */
	public boolean hasBookWithISBN(long isbn) {
		for (Book b : books) {
			if (b.getISBN() == isbn) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gives the index of a book with a particular ISBN in ArrayList books.
	 * @param long isbn
	 * @return int inde of the book with ISBN isbn.
	 */
	public int getBookIndex(long isbn) {
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getISBN() == isbn) {
				return i;
			}
		}
		return -1;
	}

}
