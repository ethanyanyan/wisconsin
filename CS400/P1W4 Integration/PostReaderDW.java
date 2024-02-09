import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This class reads reddit post data from a text file in which each post is
 * stored as a single line of text. Each of these lines contains a post title,
 * then url, and then body text in that order and these three fields are
 * separated by a three character delimiter: +++.
 * 
 * @author DataWrangler, courtesy of the CS400 course staff.
 */
public class PostReaderDW implements PostReaderInterface {

	/**
	 * This method reads each line from the specified file, and stores Post objects
	 * with corresponding data into a list that is then returned.
	 * 
	 * @param filename contains the path and name of the data file that should be
	 *                 read from
	 * @return List of post read from file
	 * @throws FileNotFoundException when a file corresponding to the provided
	 *                               filename cannot be read from
	 */
	public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException {
		// use scanner to read from the specified file, and store results in posts
		ArrayList<PostInterface> posts = new ArrayList<>();
		Scanner in = new Scanner(new File(filename));

		while (in.hasNextLine()) {
			// for each line in the file being read:
			String line = in.nextLine();
			// split that line into parts around around the delimiter: +++
			String[] parts = line.split("\\+\\+\\+");
			// most lines should have all three parts
			if (parts.length == 3)
				posts.add(new PostDW(parts[0], parts[1], parts[2]));
			// although are missing a body: we'll use empty string in those cases
			if (parts.length == 2)
				posts.add(new PostDW(parts[0], parts[1], ""));
			// for lines with any other number of parts, we'll print a warning message
			if (parts.length < 2 || parts.length > 3)
				System.out.println("Warning: found a line without exactly 2 or 3 parts: " + line);
		}

		// then close the scanner before returning the list of posts
		in.close();
		return posts;
	}
}
