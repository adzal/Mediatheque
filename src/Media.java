import java.io.Serializable;

public abstract class Media implements Serializable {
	protected String title;
	protected String mediaType;
	protected int noAvailable = 1;

	public Media(String title, String mediaType) {
		this.title = title;
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return title + ", mediaType=" + mediaType;
	}

	public String display() {
		return title;
	}

	protected String getAvailability() {
		return noAvailable > 0 ? "" : "(out of stock)";
	}
}
