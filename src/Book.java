
public class Book extends Media {
	protected int pages;

	public Book(String title, int pages) {
		super(title, "Book");
		this.pages = pages;
	}

	public Book(String title, int pages, String mediaType) {
		super(title, mediaType);
		this.pages = pages;
	}

	public String display() {
		return title + ", " + pages + " pages" +getAvailability() ;
	}
}
