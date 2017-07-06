package WordDensityAnalysis;
import java.util.*;
/*
 * Root of a Trie. A node have a value/word, frequency and childs.
 */
public class TrieNode {

	private String word;
	private int freq;
	private List<TrieNode> childs;
	
	//Constructor to initialze a word as Node of a Trie.
	// This initial frequency is set to 1.
	public TrieNode(String word){
		this.word = word;
		this.freq = 1;
		this.childs = new ArrayList<TrieNode>();
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public List<TrieNode> getChilds() {
		return childs;
	}

	public void setChilds(List<TrieNode> childs) {
		this.childs = childs;
	}

	public TrieNode findChild(String word){
		for(TrieNode child : childs)
		 if(child.word.equals(word))
			 return child;
		return null;
	}
}
