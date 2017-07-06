package WordDensityAnalysis;

import java.util.*;

/*
 * Its a Trie of words. The root is "*". A new word added as a child to the root.
 */
public class Trie {

	private TrieNode root = null;
	private Map<String, TrieNode> wordNodes = null;
	private Map<String, Integer> frequencyTable = null;

	public Trie() {
		root = new TrieNode("*");
		wordNodes = new HashMap<String, TrieNode>();
		frequencyTable = new HashMap<String, Integer>();
	}

	/*
	 * This function will create a node for a new word and then it will add it
	 * to root child list. If the word is already present it will just increase the frequency.
	 */
	void addNode(String word) {
		word = word.toLowerCase();
		if (!wordNodes.containsKey(word)) {
			TrieNode node = new TrieNode(word);
			wordNodes.put(word, node);
			root.getChilds().add(node);

		} else {
			TrieNode node = wordNodes.get(word);
			node.setFreq(node.getFreq() + 1);
		}
	}

	/* This function will add multiple words to the trie. It checks for each
	 * word if its a child of an existing node. If yes it adds to that node.
	 */
	void addNode(String[] words) {
		TrieNode parent = root;

		for (String word : words) {
			word = word.toLowerCase();
			TrieNode child = parent.findChild(word);

			if (child == null) {
				child = new TrieNode(word);
				List<TrieNode> childOfParent = parent.getChilds();
				childOfParent.add(child);
			}
			parent = child;

		}

	}

	/*
	 * Function to get k high frequency words in the trie.
	 */
	public List<String> getMostUsedKeyWords(int k) {
		for (TrieNode startNode : root.getChilds())
			getKeyWords(startNode, "");
		TreeSet<Integer> frequncies = new TreeSet<Integer>();
		HashMap<Integer, List<String>> map = new HashMap<Integer, List<String>>();

		for (String key : frequencyTable.keySet()) {
			int frequency = frequencyTable.get(key);
			if (!map.containsKey(frequency))
				map.put(frequency, new ArrayList<String>());
			map.get(frequency).add(key);
			frequncies.add(frequency);
			if (frequncies.size() > k)
				frequncies.pollFirst();
		}

		List<String> list = new ArrayList<String>();
		while (!frequncies.isEmpty()) {
			int freq = frequncies.pollLast();
			if(freq !=1)// word with frequency 1 probably does not signfy any thing
			System.out.println("KeyWord:" + map.get(freq));
			list.addAll(map.get(freq));
		}
		return list;
	}

	private void getKeyWords(TrieNode node, String s) {
		if (s.length() == 0)
			frequencyTable.put(s + node.getWord(), node.getFreq());
		else
			frequencyTable.put(s + " " + node.getWord(), node.getFreq());

		for (TrieNode child : node.getChilds()) {
			if (s.length() == 0)
				getKeyWords(child, s + node.getWord());
			else
				getKeyWords(child, s + " " + node.getWord());
		}

	}

	public TrieNode getRoot() {
		return root;
	}
}
