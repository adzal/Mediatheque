import java.io.Serializable;

public abstract class Media implements Serializable {
	protected String title;
	protected int noAvailable = 1;

	public Media(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return title + ", mediaType=" + getClass().getName();
	}

	public String display() {
		return title;
	}

	protected String getAvailability() {
		return noAvailable > 0 ? "" : "(out of stock)";
	}
}
