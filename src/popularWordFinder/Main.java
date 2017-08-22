package popularWordFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.StringTokenizer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Main {
	
	final static String FILE_NAME = "popular_words.txt";
	final static String WEBSITE = "http://www.onet.pl/";
	final static int DEMAND = 10;

	public static void main(String[] args) {
		saveHeadingsToFile();
		readHeadingsFromFile();
	}
	
	static void saveHeadingsToFile() {
		Connection connect = Jsoup.connect(WEBSITE);
		try {
			Document document = connect.get();
			Elements links = document.select("span.title");
			
			String headings = "";
			for (Element elem : links) {
				headings += elem.text() + " ";
			}
			StringTokenizer strTok = new StringTokenizer(headings, " \t\n\r\f,.:;?![]'\"");
			
			ArrayList<String> out = new ArrayList<>();
			while (strTok.hasMoreTokens()) {
				out.add(strTok.nextToken());
			}
			Path path = Paths.get(FILE_NAME);
			Files.write(path, out);
			
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	static void readHeadingsFromFile() {
		Path path = Paths.get(FILE_NAME);
		ArrayList<String> in = new ArrayList<>();
		try {
			in.addAll(Files.readAllLines(path));
			
			ArrayList<String> excluded = new ArrayList<>();
			ArrayList<String> out = new ArrayList<>();
			for (String word : in) {
				if (word.length() <= 3) {
					excluded.add(word);
				} else {
					out.add(word.toLowerCase());
				}
			}
			String str = FILE_NAME.substring(0, FILE_NAME.lastIndexOf('.')) 
					+ "_excluded"
					+ FILE_NAME.substring(FILE_NAME.lastIndexOf('.'));
			Path pathExcluded = Paths.get(str);
			Files.write(pathExcluded, excluded);
			
			//most popular
			//version after third week
			Map<String, Integer> popularWords = new HashMap<>();
			for (String word : out) {
				if (popularWords.containsKey(word)) {
					popularWords.put(
							word, 
							popularWords.get(word) + 1);
				} else {
					popularWords.put(word, 1);
				}
			}
			
			out = new ArrayList<>();
			for (int i = 0; i < DEMAND; i++) {
				int topWord = 0;
				for (int highest : popularWords.values()) {
					topWord = (highest>topWord) ? highest : topWord;
				}
				for (String key : popularWords.keySet()) {
					if (popularWords.get(key) == topWord) {
						out.add(key);
						popularWords.remove(key);
						break;
					}
				}
			}

			Path pathMost = Paths.get("most_" + FILE_NAME);
			Files.write(pathMost, out);
			
			/*version after first week
			ArrayList<String> unique = new ArrayList<>();
			out.sort(String::compareToIgnoreCase);
			String[] outStringArray = out.toArray(new String[0]);
			unique.add(outStringArray[0]);
			for (int i = 1; i < outStringArray.length; i++) {
				if (!outStringArray[i].equals(outStringArray[i-1])) {
					unique.add(outStringArray[i]);
				}
			}			
			int[] frequency = new int[unique.size()];
			int i = 0;
			for (String word : unique) {
				frequency[i] = Collections.frequency(out, word);
				i++;
			}
			
			int[] popularIndexes = mostPopular(frequency);
			ArrayList<String> popularWords = new ArrayList<>();
			for (i = 0; i < popularIndexes.length; i++) {
				popularWords.add(unique.get(popularIndexes[i]));
			}
			*/
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static int[] mostPopular(int[] in) {
		int[] out = new int[10];
		
		for (int i = 0; i < out.length; i++) {
			int max = 0;
			for (int j = 1; j < in.length; j++) {
				if (in[j] > in[max]) {
					max = j;
				}
			}
			out[i] = max;
			in[max] = 0;
		}
		return out;
	}

}
