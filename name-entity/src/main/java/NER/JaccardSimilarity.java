package NER;

import java.util.HashSet;
import java.util.Set;

public class JaccardSimilarity {
    public static void main(String[] args) {
        String text1 = "Burj Al-Arab Jumeirah";
        String text2 = "Burj Al Arab";
        
        // Tokenize the strings
        Set<String> set1 = tokenize(text1);
        Set<String> set2 = tokenize(text2);
        
        // Calculate Jaccard Similarity
        double similarity = calculateJaccardSimilarity(set1, set2);
        System.out.println("Jaccard Similarity: " + similarity);
    }
    
    private static Set<String> tokenize(String text) {
        // Convert text to lowercase and split into tokens based on non-alphanumeric characters
        String[] tokens = text.toLowerCase().split("\\W+");
        Set<String> tokenSet = new HashSet<>();
        for (String token : tokens) {
            if (!token.isEmpty()) {
                tokenSet.add(token);
            }
        }
        return tokenSet;
    }
    
    private static double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
        // Calculate intersection
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        
        // Calculate union
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        
        // Return Jaccard Similarity
        return (double) intersection.size() / union.size();
    }
}
