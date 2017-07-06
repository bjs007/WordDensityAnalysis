package WordDensityAnalysis;
import java.net.URL;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
/* 
 * This parser first read the document with the help of Jsoup.connect.
 * It initializes the word Parser which the document obtained.
 * Calls word Parser doParse() Method to make word trie out of document.
 * calls getMostUsedKeyWords on the tried obtained to get the topic of web url ( high frequency words).
 */
public class Parser {
	private Trie trie =null;
	private String url = null;
	Document doc = null;
	WordParser parser = null;
	
	public Parser(String url) {
		this.url = url;
		
	}
	
	/*
	 * Reading the web page
	 */
	public Document getPage(String urlString) {
		Document document = null;
		try {
			document = Jsoup.connect(urlString.toString()).get();
		} catch (Exception e) {
			System.out.println("Document can't be parsed");
		}
		
		return document;
	}
	
	/*
	 * initialzing the word parser to make trie. Calling getMostUsedKeyWords on the trie to get the topic
	 */
	
	public void parseDocument(){
		doc = getPage(url);
		parser = new WordParser(doc);
		this.trie = parser.doParsing();
		this.trie.getMostUsedKeyWords(6);
	}

}
