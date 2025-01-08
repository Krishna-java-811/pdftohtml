package NER.name_entity;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;
import java.util.List;

public class DayExtractorWithLocalClassifier {
    public static void main(String[] args) {
        // Set up Stanford CoreNLP pipeline properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        
        // Specify the path to your local classifier
        props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");

        // Initialize the StanfordCoreNLP pipeline with the specified properties
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Sample text
        String text = "Day 1\n";

        // Create an annotation object
        Annotation document = new Annotation(text);

        // Run the pipeline on the text
        pipeline.annotate(document);

        // Iterate through sentences and tokens
        for (CoreMap sentence : document.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.word();
                String ne = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);

                // Print named entities and their tags
                System.out.println("Word: " + word + ", NE Tag: " + ne);

                // Check for custom patterns (e.g., "Day" followed by a number)
                if (ne.equalsIgnoreCase("DURATION") && word.equalsIgnoreCase("Day")) {
                    // Look at the next token to check if it's a number
                    List<CoreLabel> tokens = sentence.get(CoreAnnotations.TokensAnnotation.class);
                    int index = tokens.indexOf(token);
                    if (index + 1 < tokens.size()) {
                        CoreLabel nextToken = tokens.get(index + 1);
                        if (nextToken.get(CoreAnnotations.NamedEntityTagAnnotation.class).equals("NUMBER")) {
                            System.out.println("Day count: " + nextToken.word());
                        }
                    }
                }
            }
        }
    }
}
