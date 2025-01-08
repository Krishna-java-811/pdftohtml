package NER;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

public class StanfordNLPStringComparison {
    public static void main(String[] args) {
    	 Properties props = new Properties();
         props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
         // Specify the path to the model files
         props.setProperty("pos.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english-left3words-distsim.tagger");
         props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");
         StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

         // Input strings
         String text1 = "Dubai";
         String text2 = "dubai";

        // Process the texts
        List<String> lemmas1 = getLemmas(pipeline, text1);
        List<String> lemmas2 = getLemmas(pipeline, text2);

        // Compare the lists of lemmas
        double similarity = calculateJaccardSimilarity(lemmas1, lemmas2);
        System.out.println("Jaccard Similarity: " + similarity);
    }

    // Method to extract lemmas from text using Stanford CoreNLP
    public static List<String> getLemmas(StanfordCoreNLP pipeline, String text) {
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        List<String> lemmas = new ArrayList<>();
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                lemmas.add(lemma);
            }
        }
        return lemmas;
    }

    // Method to calculate Jaccard similarity between two lists of strings
    public static double calculateJaccardSimilarity(List<String> list1, List<String> list2) {
        Set<String> set1 = new HashSet<>(list1);
        Set<String> set2 = new HashSet<>(list2);

        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);

        return (double) intersection.size() / union.size();
    }
}

