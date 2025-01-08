package NER.name_entity;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class DayCountExtractor1 {
    public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {
        Long startTime = System.currentTimeMillis();
        String serializedClassifier = "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz";
        CRFClassifier classifier = CRFClassifier.getClassifier(serializedClassifier);

        String input = "Day 1\n" + "\n";
        List<CoreLabel> sentences = new ArrayList<>();
        String[] tokens = input.split(" ");
        for (String token : tokens) {
            CoreLabel sentence = new CoreLabel();
            sentence.setWord(token);
            sentences.add(sentence);
        }

        classifier.classify(sentences);

        for (CoreLabel sentence1 : sentences) {
            String mentionText = sentence1.word();

            // Extract the day count from the mention text
            int dayCount = extractDayCount(mentionText);
            System.out.println("Day count: " + dayCount);
        }
        Long endTime = System.currentTimeMillis();
        Long executionTime = endTime - startTime; // This is in milliseconds
        double executionTimeInSeconds = executionTime / 1000.0; // Convert to seconds
        System.out.println("The model execution time is : " + executionTimeInSeconds + " seconds");
    }

    private static int extractDayCount(String mentionText) {
        if (mentionText.startsWith("Day")) {
            String[] parts = mentionText.split(" ");
            if (parts.length > 1) {
                try {
                    return Integer.parseInt(parts[1]);
                } catch (NumberFormatException e) {
                    // Ignore if the second part is not a number
                }
            }
        }
        return -1; // Return -1 if no day count is found
    }
}