package NER;

import org.apache.commons.text.similarity.LevenshteinDistance;

public class NormalizedLevenshteinExample {
    public static void main(String[] args) {
        LevenshteinDistance distance = new LevenshteinDistance();
        String str1 = "Burj Al Arab";
        String str2 = "Burj Al-Arab Jumeirah";

        int maxLen = Math.max(str1.length(), str2.length());
        int levenshteinDist = distance.apply(str1, str2);
        double normalizedSimilarity = 1 - (double) levenshteinDist / maxLen;

        System.out.println("Normalized Levenshtein Similarity: " + normalizedSimilarity); // Output: ~0.571
    }
}
