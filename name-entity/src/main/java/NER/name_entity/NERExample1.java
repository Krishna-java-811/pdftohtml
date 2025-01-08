package NER.name_entity;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreLabel;

import java.util.List;
import java.util.Properties;

public class NERExample1 {
	public static void main(String[] args) {
		// Text to be processed
		String text = "Fine Dining: \n"
				+ "\n"
				+ "*  The Library by Zuma  (Downtown Dubai): Exquisite Lebanese cuisine with stunning city views.\n"
				+ "*  The Black Pearl  (Jumeirah Beach Hotel): Elegant restaurant with a focus on fresh seafood and Mediterranean dishes.\n"
				+ "*  Pierchic  (Dubai Marina): Elegant French brasserie with a terrace overlooking the marina.\n"
				+ "*  The Veranda Restaurant  (The Palace Downtown): Classic British fare in a sophisticated setting.\n"
				+ "*  Il Teatro  (Dubai Mall): Italian restaurant with a contemporary ambience and a wide selection of dishes.\n"
				+ "\n"
				+ " Mid-Range Restaurants: \n"
				+ "\n"
				+ "*  Ziya's Taverna  (Downtown Dubai): Authentic Greek tavern with a cozy atmosphere and traditional cuisine.\n"
				+ "*  Al Hadheerah  (Al Barsha): Historic restaurant serving traditional Emirati cuisine in a traditional setting.\n"
				+ "*  Al Reef  (Dubai Marina): Family-owned seafood restaurant with a relaxed atmosphere and a wide selection of fresh seafood.\n"
				+ "*  Abu Loulah's Restaurant  (Dubai): Popular spot for Emirati dishes, including shawarma and machboos.\n"
				+ "*  Al Ustad's  (Jumeirah Beach Road): Lebanese restaurant with a lively atmosphere and a wide selection of dishes.\n"
				+ "\n"
				+ " Casual Restaurants: \n"
				+ "\n"
				+ "*  The Breakfast Club  (Al Barsha): Popular spot for breakfast and brunch with a wide selection of dishes.\n"
				+ "*  Al Khaima Restaurant  (Al Sufouj): Traditional Emirati restaurant with a friendly atmosphere.\n"
				+ "*  The Cheesecake Factory  (Dubai Marina): Classic American chain with a wide selection of dishes.\n"
				+ "*  Shawarma Street  (Al Barsha): Street food market with a wide variety of shawarma and other Middle Eastern dishes.\n"
				+ "*  Al Reef  (Dubai Marina): Family-owned restaurant with a relaxed atmosphere and a wide selection of dishes.\n"
				+ "\n"
				+ " Additional Tips: \n"
				+ "\n"
				+ "* Make reservations in advance, especially on weekends.\n"
				+ "* Dress modestly when dining out, especially in traditional restaurants.\n"
				+ "* Be aware of the local culture and avoid making loud or disruptive behavior.\n"
				+ "* Try the local coffee, a strong and flavorful brew.";

//        
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");

		// Specify the path to the models directory
		props.setProperty("pos.model",
				"/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");

		
		
		
		// Build the pipeline
		StanfordCoreNLP pipeline = null;
		try {
			pipeline = new StanfordCoreNLP(props);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Failed to initialize StanfordCoreNLP pipeline: " + e.getMessage());
			return;
		}

		
		  try {
              CoreDocument document = new CoreDocument(text);
              pipeline.annotate(document);
              // get confidences for entities
              if (document.entityMentions() != null) {
                  for (CoreEntityMention em : document.entityMentions()) {
                     
                   
                     
                         
               System.out.println(em.entityType() + "\t" + em.text());
                   
                      
                  }
              }
          } catch (Exception e) {
              // Handle the exception and log the error
              System.err.println("Error annotating document: " + e.getMessage());

          }
//		// Create an Annotation object with the given text
//		Annotation document = new Annotation(text);
//
//		// Annotate the document
//		pipeline.annotate(document);
//
//		// Extract sentences from the document
//		List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
//
//		// Process each sentence
//		for (CoreMap sentence : sentences) {
//			// Extract tokens and their NER labels
//			for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
//				String word = token.get(CoreAnnotations.TextAnnotation.class);
//				String ner = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
//				if (!"O".equals(ner)) {
//					System.out.println(ner + ": " + word);
//				}
//			}
//		}
	}
}