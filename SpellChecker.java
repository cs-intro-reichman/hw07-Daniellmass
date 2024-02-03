
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction); 
 }
	
	


	public static String tail(String str) {
		String tailStr = str;
		int tailLngth = tailStr.length();
		if(tailLngth <= 1) {
			tailStr = "";
			return tailStr;
		}
		else {
			tailStr = tailStr.substring(1, tailLngth);
			return tailStr;	
		}
	}

	public static int levenshtein(String word1, String word2) {
		String a = word1.toLowerCase();
		String b = word2.toLowerCase();
		if (b.length() == 0) {
			return a.length();
		}
		else if (a.length() == 0) {
			return b.length();
		}
		else if (a.charAt(0) == b.charAt(0)) {
			return levenshtein(tail(a), tail(b));
		}
		else {
		int insert = levenshtein(a, tail(b));
		int delete = levenshtein(tail(a), b);
		int sub = levenshtein(tail(a), tail(b));
		return  1 + Math.min(Math.min(delete, sub), insert);	
		}
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

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String wordL = word.toLowerCase();
		for (int i = 0; i < 3000; i++) {
			String dWord = dictionary[i];
			if (levenshtein(wordL, dWord) <= threshold && wordL.length() <= dWord.length()) {
				return dWord;
			}
					 
        }
	    for (int i = 0; i < 3000; i++) {
		    String dWord = dictionary[i];
		    if (levenshtein(wordL, dWord) <= threshold && wordL.length() >= dWord.length()) {
			return dWord;
		}	
    }
	return wordL;
  }
}
