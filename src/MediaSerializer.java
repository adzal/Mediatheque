import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MediaSerializer {
	private static final String tempDir = System.getProperty("java.io.tmpdir");

	public void backupMedia(String fileName, ArrayList<Media> availableMedia) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(tempDir + fileName);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(availableMedia);
		out.close();
		fileOut.close();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Media> restoreMedia(String fileName) throws IOException, ClassNotFoundException {
		ArrayList<Media> retVal = null;
		FileInputStream fileIn = new FileInputStream(tempDir + fileName);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		retVal = (ArrayList<Media>) in.readObject();
		in.close();
		fileIn.close();

		return retVal;
	}

}
