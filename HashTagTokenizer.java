

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
        In in = new In(fileName);
		for (int i = 0; i < 3000; i++) {
			String line = in.readLine();
			dictionary [i] = line;
		}
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary [i].equals(word)) {
				return true;			
			}
		}
		return false;
	}

	//Helper Method 

	public static boolean equals (String strF, String strS) {
		boolean ans = false;
		if (strF.length() != strS.length()) {
			return ans;
		}
		else {
			for (int i = 0; i < strF.length(); i++) {
				if (strF.charAt(i) != strS.charAt(i)) {
					return false;
				}
			}
		}
		return true;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }
        int N = hashtag.length();
		String hashtgLc = "";
		//Make sure that the hashtag is in lowercase
        for (int i = 0; i < N; i++) {
			if ((int) hashtag.charAt(i) < 97) {
				hashtgLc = hashtag.toLowerCase();
			}
			else {
				hashtgLc = hashtag;
			}
		}
		for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashtgLc.substring(0, i), dictionary) == true) {
				String printHash = hashtgLc.substring(0, i);
				System.out.println(printHash);
				String rmainHsh = hashtgLc.substring(i, N);
				breakHashTag(rmainHsh, dictionary);
			}
		}
    }
}

