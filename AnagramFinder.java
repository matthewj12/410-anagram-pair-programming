import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AnagramFinder {
	// returns a list of collections of anagrams. If the collection (the inner ArrayList) only has one string, that means that that word has no anagrams.
	public static ArrayList<ArrayList<String>> findAnagrams(String file) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader(file));
		String line = in.readLine();

		// words in words will be set to an empty string if they are part of an anagram, which is why we need to make a copy containing the original words.
		ArrayList<String> words = new ArrayList<String>();

		while (line != null) {
			words.add(line.toLowerCase());
			line = in.readLine();
		}

		// make a deep copy of words
		ArrayList<String> originalWords = new ArrayList<String>();
		for (String word : words) {
			originalWords.add(word);
		}
		
		ArrayList<ArrayList<String>> allAnagrams = new ArrayList<ArrayList<String>>();

		//2d loop go through the array if finds matching word remove the new found mathcing from the words arr so no duplicates and add to allAnagrams where matchings
		for (int i = 0; i < words.size(); i++) {//starting point to compare all other word from the file
			ArrayList<String> temp = new ArrayList<String>();
			temp.add(words.get(i));
			
			int j = i + 1;
			while (j < words.size()) {//go through all words and checks for matches
				if (areAnagrams(words.get(i), words.get(j))) {
					temp.add(words.get(j));
					words.remove(j);
				}
				else {
					j++;
				}
			}
			allAnagrams.add(temp);
		}

		return allAnagrams;
	}
	
	public static boolean areAnagrams(String a, String b) { // take two strings sort them lexographically and then see if they are the same(anagrams)
		a = a.toLowerCase();
		b = b.toLowerCase();
		
		char aCharArr[] = a.toCharArray();
		char bCharArr[] = b.toCharArray();
		
		Arrays.sort(aCharArr);
		Arrays.sort(bCharArr);
		
		String aSorted = String.valueOf(aCharArr);
		String bSorted = String.valueOf(bCharArr);

		return aSorted.equals(bSorted);
	}
}
