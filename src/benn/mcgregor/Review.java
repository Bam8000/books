package benn.mcgregor;

/**
 * Review defined by an immutable comment with a unique author and rating.
 * @author benn.mcgregor
 * @version 1.0
 */


public class Review {
	private String commenterName;
	private String comment;
	private float rating; //rating is out of 5
	
	/**
	 * Constructor method sets the comment, name of author, rating
	 * @param String name
	 * @param String comment
	 * @param float rating
	 */
	Review(String name, String comment, float rating) {
		commenterName = name;
		this.comment = comment;
		this.rating = rating;
	}
	
	/**
	 * Accessor that returns the review.
	 * @return String comment
	 */
	public String getComment() {
		return comment;
	}
	
	/**
	 * Accessor that returns the author of the comment.
	 * @return String commenterName
	 */
	public String getCommenterName() {
		return commenterName;
	}
	
	/**
	 * Accessor that returns the rating of the book.
	 * @return float rating
	 */
	public float getRating() {
		return rating;
	}
	
	/**
	 * @return essential data comment, author, rating of the review when instance is printed
	 */
	@Override
	public String toString() {
		return "\"" + comment + "\" by " + commenterName + " Rating: " + rating;
	}

}
