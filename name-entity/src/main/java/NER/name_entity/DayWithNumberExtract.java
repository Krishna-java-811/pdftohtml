package NER.name_entity;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.SentenceUtils;
import edu.stanford.nlp.ling.TaggedWord;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.tagger.io.TaggedFileRecord;
import edu.stanford.nlp.tagger.maxent.TaggerConfig;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;
import edu.stanford.nlp.util.StringUtils;

public class DayWithNumberExtract {
	static String text = "Day 1: \n" + "\n" + "* Arrival in Dubai. Check into your hotel in downtown Dubai.\n"
			+ "* Visit the Dubai Fountain show in the evening.\n" + "\n" + " Day 2: \n" + "\n"
			+ "* Visit the Dubai Mall, the world's largest mall.\n"
			+ "* Explore the Burj Khalifa, the world's tallest building.\n" + "* Enjoy dinner at the Armani Hotel.\n"
			+ "\n" + " Day 3: \n" + "\n" + "* Take a desert safari tour.\n"
			+ "* Experience camel riding, sandboarding, and a traditional barbecue dinner.\n" + "\n" + " Day 4: \n"
			+ "\n" + "* Visit the Atlantis Aquaventure Waterpark.\n" + "* Relax on the beach at Jumeirah Beach.\n"
			+ "* Enjoy a sunset cruise on a traditional dhow boat.\n" + "\n" + " Day 5: \n" + "\n"
			+ "* Visit the Dubai Miracle Garden.\n"
			+ "* Explore the Dubai Frame, an observation deck offering panoramic city views.\n"
			+ "* Take a walk through the vibrant Dubai Marina.\n" + "\n" + " Day 6: \n" + "\n"
			+ "* Visit the Dubai Museum to learn about the city's history and culture.\n"
			+ "* Explore the Spice Souk for a traditional Arabian experience.\n"
			+ "* Enjoy a delicious meal at a local restaurant.\n" + "\n" + " Day 7: \n" + "\n"
			+ "* Visit the Jumeirah Mosque, a stunning architectural marvel.\n"
			+ "* Spend the afternoon shopping at the Dubai Marina.\n"
			+ "* Enjoy a traditional shisha dinner at a rooftop restaurant.\n" + "\n" + " Day 8: \n" + "\n"
			+ "* Take a day trip to Abu Dhabi.\n"
			+ "* Visit the Sheikh Zayed Grand Mosque, the largest mosque in the Arab world.\n"
			+ "* Explore the Louvre Abu Dhabi, a contemporary art museum.\n" + "\n" + " Day 9: \n";

	public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {

		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		props.setProperty("ner.applyFineGrained", "false");
		props.setProperty("tokenize.language", "en");
		props.setProperty("ssplit.isOneSentence", "true");
		props.setProperty("ssplit.eolonly", "true");
		 props.setProperty("tokenize.whitespace", "true");

//		// Set properties for StanfordCoreNLP
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

		String serializedClassifier = "/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz";

		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
		String[] splitText = text.split("\n");
		for (String str : splitText) {
			String classifiedStr = classifier.classifyToString(str, "tabbedEntities", false);
			// Split the classified string into lines
			String[] lines = classifiedStr.split("\\r?\\n");

			// || (parts[1].equals("DATE")
			// Iterate through each line
			for (String line : lines) {
				// Split each line by tabs to get entity and type
				String[] parts = line.split("\t");
				// Check if the line has location or person type
				if (parts.length > 1) {
					String entity = parts[0];
					System.out.println(entity + "\t" + parts[1]);
				
				}
			}
		}

//		try {
//			CoreDocument document = new CoreDocument(text);
//			if (pipeline != null) {
//				pipeline.annotate(document);
//			}
//
//			if (document.entityMentions() != null) {
//				for (CoreEntityMention em : document.entityMentions()) {
//					System.out.println(em.text() + "\t" + em.entityType());
//
//				}
//
//			}
//
//		} catch (Exception e) {
//			System.err.println("Error annotating document: " + e.getMessage());
//		}

	}

}
