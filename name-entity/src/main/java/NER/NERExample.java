package NER;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.coref.data.Document;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.simple.*;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import edu.stanford.nlp.util.StringUtils;

public class NERExample {
	public static void main(String[] args) {
//		// Create StanfordCoreNLP object properties
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner"); // Exclude SUTime

		// Specify the path to the model files
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
//		// Build the pipeline
//		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		String text = "Sure, here are some restaurant recommendations for your 10-day trip:\n"
				+ "\n"
				+ "* Al Reef Restaurant: For authentic Emirati cuisine with stunning city views.\n"
				+ "* The Dubai Fish Market: A vibrant seafood market with a wide selection of fresh seafood and traditional dishes.\n"
				+ "* Al Hadheerah: A traditional Arabian restaurant with a warm atmosphere and delicious food.\n"
				+ "* The Social House: A stylish restaurant with a modern take on traditional Emirati dishes.\n"
				+ "* The Gallery Cafe: A chic restaurant with a terrace overlooking the Dubai Marina.\n"
				+ "* The Bombay Bungalow: A popular spot for both locals and tourists, serving authentic Indian cuisine.\n"
				+ "* Zaatar Restaurant: A casual restaurant with a cozy atmosphere and a wide selection of Middle Eastern dishes.\n"
				+ "* The Secret Garden: A hidden oasis in the heart of the city, serving organic and healthy cuisine.\n"
				+ "* The Butcher's Block: A popular restaurant with a wide selection of meats, seafood, and vegetarian options.";

		// set the list of annotators to run

		// build pipeline
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		// create a document object
		CoreDocument doc = new CoreDocument(text);
		// annotate
		pipeline.annotate(doc);
//	    // display tokens
//	    for (CoreLabel tok : doc.tokens()) {
//	      System.out.println(String.format("%s", tok.word()));
//	    }
		// Split the string by newline characters
		for (CoreEntityMention em : doc.entityMentions()) {
			System.out.println("\tdetected entity: \t" + em.text() + "\t" + em.entityType());
		}
//
//		for (String s : splitText) {
//			if (s.trim().isEmpty()) {
//				continue;
//			}
//			
//		   
////			CoreDocument document = new CoreDocument(s);
////			pipeline.annotate(document);
////			Sentence sent = new Sentence(s);
////			List<String> nerTags = sent.nerTags(); 
////			System.out.println(nerTags);
////			for (CoreEntityMention em : document.entityMentions()) {
////				System.out.println(em.text() + "\t" + em.entityType());
////			}
//
//		}

	}
}
