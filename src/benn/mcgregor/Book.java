package benn.mcgregor;

/**
 * Book defined by immutable title, ISBN, author, set of reviews
 * and average rating from those reviews.
 * @author benn.mcgregor
 * @version 1.0
 */

import java.util.ArrayList;

public class Book implements Comparable{
	private String title;
	private String author;
	private long isbn;
	private float avgRating = 0; //needs updating every time a review is added
	private ArrayList<Review> reviews = new ArrayList<Review>();
	
	/**
	 * Constructor sets the title, name of author, ISBN
	 * @param String title
	 * @param String author
	 * @param long isbn
	 */
	Book(String title, String author, long isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}
	
	/**
	 * Accessor that returns the book's ISBN.
	 * @return long isbn
	 */
	public long getISBN() {
		return isbn;
	}
	
	/**
	 * Accessor that returns the book's author.
	 * @return String author
	 */
	public String getAuthor() {
		return author;
	}
	
	/**
	 * Accessor that returns the book's title.
	 * @return String title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Accessor that returns the book's average rating.
	 * @return float avgRating;
	 */
	public float getAvgRating() {
		return avgRating;
	}
	
	/**
	 * Accessor that returns the reviews of the book.
	 * @return String of the reviews contained in ArrayList reviews.
	 */
	public String getReviews() {
		String rtrn = "\n";
		for (Review r : reviews) {
			rtrn += r.toString() + "\n";
		}
		return "\nThere are " + reviews.size() + " reviews for \"" + title + 
				"\" with average rating " + avgRating + ":" + rtrn;
	}
	
	/**
	 * @return essential data ISBN, title, author of the book when instance is printed
	 */
	@Override
	public String toString() {
		return "ISBN: " + isbn + " Title: " + title + 
				" Author: " + author;
	}
	
	/**
	 * compare this Book with another object and
	 * @return true if they have the same ISBN.
	 * @param Object obj — usually the other Book object.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Book) {
			Book b = (Book) obj;
			if (b.getISBN() == this.isbn) {
				return true;
			}
		} 
		return false;
	}
	
	/**
	 * Implementation of Comparable interface.
	 * Compares ISBNs of this instance and another Book instance and
	 * determines whether this instance's ISBN is greater, lesser or equal to
	 * that of the other instance.
	 * @return possibilities -1 (less than), 1 (greater than), 0 (equal)
	 * to be evaluated by Collections.sort() method.
	 */
	public int compareTo(Object obj) { //determines how books should be sorted (by ISBN)
		Book b = (Book) obj;
		if (isbn < b.getISBN()) {
			return -1;
		} else if (isbn > b.getISBN()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * Instantiates a Review object and adds it to ArrayList reviews.
	 * @param String name
	 * @param String review
	 * @param float rating
	 */
	public void addReview(Review r) {
		reviews.add(r);
		findAvgRating();
	}
	
	private void findAvgRating() {
		float sum = 0;
		for (int i = 0; i < reviews.size(); i++) {
			sum += reviews.get(i).getRating();
		}
		avgRating = sum / reviews.size();
	}

}
