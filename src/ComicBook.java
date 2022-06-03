
public class ComicBook extends Book {
	private int issueNumber;

	public ComicBook(String title, int issueNumber, int pages) {
		super(title, pages, "ComicBook");
		this.issueNumber = issueNumber;
	}

	public String display() {
		return title + ", issue no. " + issueNumber + ", " + pages + " pages" + getAvailability();
	}
}
