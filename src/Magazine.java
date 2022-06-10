
public class Magazine extends Book {
	private String month;
	private int year;

	public Magazine(String title, String month, int year, int pages) {
		super(title, pages);
		this.month = month;
		this.year = year;
	}

	public String display() {
		return title + ", " + month + " " + year + ", " + pages + " pages" + getAvailability();
	}
}
