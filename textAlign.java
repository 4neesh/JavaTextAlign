import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class textAlign {

	public textAlign(String documentName, String maxString, String align) {

		int max = Integer.parseInt(maxString);

		// use String filename to shortcut args[0] to a folder or location
		// String filename = "C:/Users/..." + documentName + ".txt";
		// then change line 15 from "documentName" to "filename"

		File textfile = new File(documentName);

		try {
			Scanner in = new Scanner(textfile);
			// validate the length of output is => the longest word length
			String longestWord = "";
			String current;

			while (in.hasNext()) {
				current = in.next();
				if (current.length() > longestWord.length()) {
					longestWord = current;
				}
			}

			// validate the entry of text file location and alignment specification

			if (longestWord.length() > max || (!align.equals("right") && !align.equals("left"))) {
				System.out.println("Width of text must be at least " + longestWord.length()
						+ " and alignment must be 'right' or 'left'");
				in.close();
				System.exit(0);
			} else {
				in.close();
				Scanner inn = new Scanner(textfile);

				// perform output
				while (inn.hasNextLine()) {
					String alignText;
					if (align.equals("right")) {
						alignText = "%" + max + "s%n";
					} else {
						alignText = "%s%n";
					}
					String line = inn.nextLine();
					while (line.length() > max - 1) {
						if (line.length() > max) {
							for (int i = 0; i < max; i++) {
								if (i != 0 && line.substring(max - i, max + 1 - i).equals(" ")) {
									System.out.printf(alignText, line.substring(0, max + 1 - i));
									line = line.substring(max + 1 - i, line.length());
									i = -1;
									if (line.length() < max) {
										System.out.printf(alignText, line);
										if (inn.hasNextLine()) {
											line = inn.nextLine();
										} else {
											break;
										}
									}
								}
							}

						} else {
							System.out.printf(alignText, line);
						}
					}
				}
				inn.close();
			}
		} catch (FileNotFoundException e) {
			// catch if file not found
			System.out.println("To use this script:java  textAlign file_name line_length alignment_side");
		}
	}

	public static void main(String[] args) {

		new textAlign(args[0], args[1], args[2]);

	}

}
