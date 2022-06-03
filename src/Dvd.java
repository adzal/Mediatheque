
public class Dvd extends Media {
	private int runningTime;

	public Dvd(String title, int runningTime) {
		super(title, "DVD");
		this.runningTime = runningTime;
	}

	public String display() {
		return title + ", " + runningTime + "mins" + getAvailability();
	}

}
