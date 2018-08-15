//HashAssign1.java
//Austin Teshuba
//This is a program for scrabble that finds all of the permutations of a word based on entered letters
import java.io.File;//imports
import java.io.FileNotFoundException;
import java.util.*;
public class HashAssign1 {
	private static HashTable<String> hash = new HashTable<String>();//start with an empty hash table
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Scanner newScan = new Scanner(new File("dictionary.txt"));//read in the data from the dictionary
			while (newScan.hasNextLine()) {//while there is a next line
				hash.add(newScan.nextLine());//add the next line to the hashtable
				//System.out.println("Yes");
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Scanner stdin = new Scanner(System.in);//make a scanner to get the word input
		System.out.println("Please input the letters");//ask to input a word
		String inpChar = stdin.nextLine();//get the inputted letters

		permutations(inpChar);//call permutations with the input as a param
	}
	
	public static void permutations(String inp) {
		//System.out.println("meep");
		perm(inp, "", new ArrayList<String>());//call the recursive perm function with an empty ans string, the letters string, and an empty "done" arraylist
	}
	private static void perm(String letters, String ans, ArrayList<String> done) {
		if (letters.length()==0) {//if there are no more letters left to use
			//System.out.println("happened");
			if (hash.has(ans) && done.contains(ans)==false) {//if we have not already printed this answer, and it is in the hashtable
				done.add(ans);//add to the array list so it is kept track of
				System.out.println(ans);//print out the possible word
			}
		} else {//otherwise
			for (int i=0; i<letters.length(); i++) {//go through each remaining letter and add it to the answer string as a possible permutation. remove the letter from available letters, and pass in the same array list.
				if (i!=0 && i!=letters.length()-1) {
					perm(letters.substring(0,i)+ letters.substring(i+1), ans+letters.substring(i,i+1), done);
					//System.out.println("Yes");
				}
				else if (i==0) {
					perm(letters.substring(i+1), ans+letters.substring(i,i+1), done);
					//System.out.println("Here");
				}
				else if (i==letters.length()-1) {
					perm(letters.substring(0, i), ans + letters.substring(i), done);
					//System.out.println("Mee");
				}
				}
			}
		}

}
