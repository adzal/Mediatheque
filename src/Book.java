
public class Book extends Media {
	protected int pages;

	public Book(String title, int pages) {
		super(title);
		this.pages = pages;
	}

	public String display() {
		return title + ", " + pages + " pages" + getAvailability();
	}
}
