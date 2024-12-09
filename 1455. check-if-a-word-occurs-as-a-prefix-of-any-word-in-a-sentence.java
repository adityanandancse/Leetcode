// 2/12/2024
//   Question no. 1455 (https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/description/)
//                      Solution in Java :

    class Solution {
    class TrieNode {
        int idx = -1;
        Map<Character , TrieNode> children = new HashMap<>();
    }
    private TrieNode root = new TrieNode();

    private void trieInsert(String word, int index) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            curr.children.putIfAbsent(ch, new TrieNode());
            curr = curr.children.get(ch);
            if (curr.idx == -1) {
                curr.idx = index;
            }
    }
    }
    private int trieSearch(String prefix) {
        TrieNode curr= root;
        for (char ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return -1;
            }
            curr = curr.children.get(ch);
        }
        return curr.idx;
    }
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            trieInsert(words[i], i + 1);
        }
        return trieSearch(searchWord);
    }
}
