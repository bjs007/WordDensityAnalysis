package WordDensityAnalysis;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/*
 * This is the Drive class having main method. It first validate the string url. Then initializes the Parser with url string.
 * Calls parseDocument() method of parser to parse the web page and pring the most relevant words to describe it.
 */
public class WordDensityDriver {

	private String url; //url string of web page.
	private Parser parser; // Parser takes an ulr. Parse it with jsoup API and uses word parser to find high frequency word.
	
	public WordDensityDriver(String urlString) {
		this.url = urlString;	
	}
	
	public Parser initParser(String url){
		parser = new Parser(url);
		
		return parser;
	}

	// Utility Function to validate an url.
	private static boolean validateURL(String urlString) {
		
		try {
		    URL u = new URL(urlString);
		    u = null;
			return true;
		} catch (Exception e) {
			System.out.println("Invalid URL");
			e.printStackTrace();
		}

		return false;
	}

	public static void main(String args[]) throws IOException {
		if (args.length < 1 || args.length >= 2)
			System.out.println("Invalid number of argument!");

		String url = args[0];
	    validateURL(url);
		WordDensityDriver wordDensityDriver = new WordDensityDriver(url);
		Parser par = wordDensityDriver.initParser(url); //initilize the parser with web url
		par.parseDocument();	// call parseDocument() function of Parser to parse the web page and print the topic of the page

	}

}
