package NER;

import org.apache.commons.text.similarity.JaroWinklerDistance;

public class JaroWinklerExample {
    public static void main(String[] args) {
        JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
        String text1 = "Dubai";
        String text2 = "Singapore";

        double similarity = jaroWinkler.apply(text1, text2);
        System.out.println("Jaro-Winkler Similarity: " + similarity);
    }
}