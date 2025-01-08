package NER;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.List;
import java.util.Properties;

public class DurationEntityExtractor {

	public static void main(String[] args) {
		// Set up Stanford CoreNLP pipeline properties
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner"); // Exclude SUTime

		// Specify the path to the model files
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
		// Build Stanford CoreNLP pipeline
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		// Sample text
		String text = "Sure, here are some recommended restaurants in Dubai:\n" + "\n"
				+ "* Al Reefea Restaurant for authentic Emirati cuisine\n"
				+ "* The Social House for a casual dining experience\n"
				+ "* Al Hadheerah Restaurant for traditional Arabian dishes\n"
				+ "* The Rivea for Italian cuisine with stunning city views\n"
				+ "* Pierchic for a seafood restaurant with a lively atmosphere\n"
				+ "* The Hummus Place for a casual and authentic hummus experience\n"
				+ "* The Al Fanar Restaurant for a rooftop dining experience with panoramic views\n"
				+ "* Zaatar W Grill for a Lebanese and Syrian grill experience\n"
				+ "* The Pink Restaurant for a unique and stylish dining experience";

		try {
			CoreDocument document = new CoreDocument(text);
			pipeline.annotate(document);
			// get confidences for entities
			if (document.entityMentions() != null) {
				for (CoreEntityMention em : document.entityMentions()) {

				System.out.println(em.text() + "\t" + em.entityType());

				}
			}
		} catch (Exception e) {
			// Handle the exception and log the error
			System.err.println("Error annotating document: " + e.getMessage());

		}

//		// Create an Annotation object with the text
//		Annotation document = new Annotation(text);
//
//		// Annotate the document with the pipeline
//		pipeline.annotate(document);
//
//		// Get the sentences from the document
//		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//
//		// Iterate through sentences and tokens to extract named entities
//		for (CoreMap sentence : sentences) {
//			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//				String word = token.get(CoreAnnotations.TextAnnotation.class);
//				String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//
//				// Check if the entity is a duration
//				// if ("DURATION".equals(ner)) {
//				System.out.println(word + " - " + ner);
//				// }
//			}
//		}
	}
}
