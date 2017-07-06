package WordDensityAnalysis;

import java.util.*;
import java.io.*;

import org.jsoup.nodes.Document;


/*
 *  It reads Punctuation, and Stop words into List and Set.
 *  It reads document word by word and add to the trie.
 */
public class WordParser {

	private Trie trie; //to stores word trie formed from Jsoup document.
	private Set<String> stopwords; // set of words whose frequency should not be counted while parsing a web page.
	private Document document; // Jsoup document to be converted to trie.
	List<String> punctuations = null; // list of panctuations to be ignored while parsing a word.

	
public	WordParser(Document document) {
		this.document = document;
		trie = new Trie();
		this.punctuations = getPunctuation();
		stopwords = getStopWords(); 
	}
/*
 * function to load stopwords from stopwords.txt
 */
	public Set<String> getStopWords() {
		Set<String> words = new HashSet<String>();
		try {
			InputStream is = getClass().getResourceAsStream("stopwords.txt");
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String sCurrentLine = null;
			while ((sCurrentLine = br.readLine()) != null)
				words.add(sCurrentLine);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return words;

	}
	/*
	 * function to make a list of punctuations to be removed from words
	 */
	public List<String> getPunctuation(){
		List<String> panctuations = new ArrayList<String>();
		panctuations.add("!");
		panctuations.add("?");
		panctuations.add("\\");
		panctuations.add("/");
		panctuations.add(",");
		panctuations.add("(");
		panctuations.add(")");
		panctuations.add("<");
		panctuations.add(">");
		panctuations.add("#");
		panctuations.add("\"");
		panctuations.add("+");
		panctuations.add(":");
		panctuations.add("=");
		panctuations.add("--");
		panctuations.add("*");
		panctuations.add("'");
		return panctuations;
	}
	
	public String replacePanctuation(String s){
		String string = s.trim();
		for(String punct: punctuations){
			string = string.replace(punct, "");
		}
		if (string.startsWith("'") || string.endsWith("'")){
			string=string.replace("'", "");
		}
		
		if (string.endsWith(".")){
			string=string.replace(".", "");
		}
		
		return string;
	}
	
	public Trie doParsing(){
		Scanner sc = new Scanner(document.text());
		StringBuilder sb=new StringBuilder();
		while(sc.hasNext()){
			String word = replacePanctuation(sc.next());
			String temp = word;
		    if(stopwords.contains(temp.toLowerCase())){
		    	if(sb.length() > 0) trie.addNode(sb.toString().split(" "));
		    	sb.setLength(0);
				continue;
		    }
		    if(isCandidateWord(word)){
				sb.append(word+" ");
				trie.addNode(word);
			}
		}
		
		sc.close();
		return trie;
	}
	
	
private boolean isCandidateWord(String s){
		
		if(s.length()==1&&s.charAt(0)-'0'==9955){ // '?'
			return false;
		}
		
		boolean isEmpty=true;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c!=' '&&c-'0'!=112) isEmpty=false;
		}
		return !isEmpty;
	}
	

}
