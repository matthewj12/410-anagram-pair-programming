import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class AnagramFinder {
	public static void anagramFinder(String file) throws IOException {
		 BufferedReader in= new BufferedReader(new FileReader(file));
		String line = in.readLine();
		String text = "";
		 while(line != null) {
			 text += line + " ";
			 line = in.readLine();
		 }
		String[] words = text.split(" ");
		ArrayList<Anagrams> allAnagrams = new ArrayList<Anagrams>();
		//2d loop go through the array if finds matching word remove the new found mathcing from the words arr so no duplicates and add to allAnagrams where matchings
		for(int i =0;i< words.length;i++) {//starting point to compare all other word from the file
			if(words[i].equals("")) {// if the current is blank skip it
				continue;
			}
			Anagrams temp = new Anagrams();
			temp.addTo(words[i].toLowerCase());
			for( int x=i+1;x<words.length;x++) {//go through all words and checks for matches
				if(anagramChecker(words[i],words[x])) {
					temp.addTo(words[x].toLowerCase());
					words[x] = ""; // turn blank after finding a match
				}
			}
			if(!temp.isEmpty()) { // if matching anagrams are found then add it to the allAnagrams
				allAnagrams.add(temp);
			}
		}
		for(Anagrams finished: allAnagrams) {
			System.out.println(finished.toString());
		}
	}
	public static boolean anagramChecker(String First, String Second) { // take two strings sort them lexographically and then see if they are the same(anagrams)
		String lowerFirst =First.toLowerCase();
		String lowerSecond=Second.toLowerCase();
		char firstArr[] = lowerFirst.toCharArray();
		Arrays.sort(firstArr);
		String sortFirst = toString(firstArr);
		char secondArr[] = lowerSecond.toCharArray();
		Arrays.sort(secondArr);
		String sortSecond = toString(secondArr);
		if (sortFirst.equals(sortSecond)){
			return true;
		}
		return false;
	}
	public static String toString(char[] word) { //bring back the char arr as string 
		String wholeWord ="" ;
		for (char letter: word) {
			wholeWord +=letter;
		}
		return wholeWord;
		
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		anagramFinder("src/anagram");
	}

}
