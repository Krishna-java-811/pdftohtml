package NER;

import info.debatty.java.stringsimilarity.SorensenDice;

public class SorensenDiceExample {
    public static void main(String[] args) {
        SorensenDice sorensenDice = new SorensenDice(2); // Use bigrams
        String text1 = "Burj Al-Arab Jumeirah";
        String text2 = "Burj Al Arab";
        double result = sorensenDice.similarity(text1, text2);
        System.out.println("Sorensen-Dice Coefficient: " + result); // Output: ~0.67
    }
}
