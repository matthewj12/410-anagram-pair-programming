import java.util.ArrayList;
public class Anagrams {
	public ArrayList<String> matching = new ArrayList<String>();
	int total;
	public void addTo(String word) {//add a new string that matches the anagram to the object if already in the matching list just increase count
		if(matching.size() ==0) {
			matching.add(word);
		}else
			if (!matching.contains(word)) { // if the new word being checked already exists break the loop don't add
				matching.add(word);
			}
		total++;
	}
	public boolean isEmpty() { // if the word has no anagrams found it will only contain the og word
		if(matching.size() ==1){
			return true;
		}
		return false;
		
	}
	public String toString() { // print out all words that are in the anagram and how often they appear
		String temp = "Anagrams:";
		for (String x : matching){
			temp += x + ", ";
		}
		temp += " Amount: " + total;
		return temp;
		
	}

}
