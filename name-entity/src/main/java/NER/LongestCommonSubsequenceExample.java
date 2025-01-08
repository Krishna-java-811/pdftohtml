package NER;

import org.apache.commons.text.similarity.LongestCommonSubsequence;

public class LongestCommonSubsequenceExample {
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int result = lcs.apply("Burj Al Arab", "Burj Al-Arab Jumeirah");
        System.out.println("Longest Common Subsequence Length: " + result); // Output: 2
    }
}