package NER.name_entity;

import com.google.common.io.Files;
import edu.stanford.nlp.dcoref.CorefChain;
import edu.stanford.nlp.dcoref.CorefCoreAnnotations.CorefChainAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations.CollapsedCCProcessedDependenciesAnnotation;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreeCoreAnnotations.TreeAnnotation;
import edu.stanford.nlp.util.CoreMap;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * A simple corenlp example ripped directly from the Stanford CoreNLP website
 * using text from wikinews.
 */
public class TruecaseExample {
	static String text = "A 8-day holiday trip in Dubai can be an exciting and memorable experience. Here's a suggested itinerary for your trip:\n"
			+ "\n" + "Day 1: Arrival and Relaxation\n"
			+ "- Arrive at Dubai International Airport and check-in at your hotel.\n"
			+ "- Spend the day relaxing and recovering from your journey. You might want to explore the local area around your hotel or take it easy by the pool.\n"
			+ "\n" + "Day 2: Explore Old Dubai\n"
			+ "- Visit the historic Al Fahidi Neighborhood and explore the traditional houses and art galleries.\n"
			+ "- Take a boat ride along the Dubai Creek and visit the Gold and Spice Souks.\n"
			+ "- Visit Jumeirah Mosque and learn about Islamic culture.\n" + "\n" + "Day 3: Shopping and Modern Dubai\n"
			+ "- Spend the day shopping at the Mall of the Emirates or the Dubai Mall.\n"
			+ "- Visit the Ski Dubai indoor ski resort or the Dubai Aquarium and Underwater Zoo.\n"
			+ "- In the evening, enjoy a traditional Arabic dinner and a show at Al Hadheerah, located in Bab Al Shams Desert Resort & Spa.\n"
			+ "\n" + "Day 4: Desert Safari\n"
			+ "- Spend the day exploring the desert. Go on a desert safari tour and enjoy a camel ride, sandboarding, and a traditional Arabic dinner under the stars.\n"
			+ "\n" + "Day 5: Water Park and Beach\n" + "- Spend the day at Wild Wadi Water Park or at Jumeirah Beach.\n"
			+ "- In the evening, take a leisurely stroll along the Jumeirah Beach Walk and enjoy dinner at one of the many restaurants.\n"
			+ "\n" + "Day 6: Museums and Cultural Sites\n" + "- Visit the Dubai Museum located in the Al Fahidi Fort.\n"
			+ "- Spend the afternoon at the Sheikh Mohammed Centre for Cultural Understanding, where you can learn about Emirati culture and traditions.\n"
			+ "\n" + "Day 7: Relaxation and Leisure\n"
			+ "- Spend the day relaxing and enjoying the facilities at your hotel. You might want to try a spa treatment or take a dip in the pool.\n"
			+ "- In the evening, take a sunset desert safari tour and enjoy a traditional Arabic dinner while watching the sunset over the desert.\n"
			+ "\n" + "Day 8: Last Day in Dubai\n"
			+ "- Spend your last day in Dubai exploring any sights or activities that you may have missed.\n"
			+ "- Do some last-minute shopping or sightseeing before heading to the airport for your departure.";

	public static void main(String[] args) throws IOException {
		// creates a StanfordCoreNLP object, with POS tagging, lemmatization, NER,
		// parsing, and coreference resolution
		Properties props = new Properties();
		props.put("annotators", "tokenize, ssplit, pos, lemma, truecase");
		// Load custom models if necessary
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");

		StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
		String input = text;
		String lcInput = input.toLowerCase(); // downcase everything.

		// create an empty Annotation with just the downcased text.
		Annotation document = new Annotation(lcInput);

		// run all Annotators on this text
		pipeline.annotate(document);

		// these are all the sentences in this document
		// a CoreMap is essentially a Map that uses class objects as keys and has values
		// with custom types
		List<CoreMap> sentences = document.get(SentencesAnnotation.class);

		// capture the true cased tokens for evaluation.
		List<String> tcTokens = new ArrayList<String>();

		System.out.println("------ begin truecase output -----");
		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String text = token.get(TextAnnotation.class);
				String trueCase = token.get(TrueCaseAnnotation.class);
				String trueCaseText = token.get(TrueCaseTextAnnotation.class);
				System.out.printf("input:%s state:%s output:%s\n", text, trueCase, trueCaseText);
				tcTokens.add(trueCaseText);
			}
		}
		System.out.println("------ end truecase otuput -----");

		// create an empty Annotation with just the standard text.
		document = new Annotation(input);

		// run all Annotators on this text
		pipeline.annotate(document);
		sentences = document.get(SentencesAnnotation.class);

		// capture the standard tokens for evaluation - note this assumes that
		// the pipeline won't generate additional tokens for the same input.
		List<String> stdTokens = new ArrayList<String>();

		for (CoreMap sentence : sentences) {
			// traversing the words in the current sentence
			// a CoreLabel is a CoreMap with additional token-specific methods
			for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
				// this is the text of the token
				String word = token.get(TextAnnotation.class);
				stdTokens.add(word);
			}
		}

		// compare the output of the tc and the original to see how well we've done
		int match = 0;
		int sz = tcTokens.size();

		System.out.println("------ begin evaluation output -----");

		for (int i = 0; i < sz; i++) {
			String tcToken = tcTokens.get(i);
			String stdToken = stdTokens.get(i);
			if (tcToken.equals(stdToken)) {
				match++;
			} else {
				System.out.printf("Truecase mismatch: input:'%s' output:'%s' @ %d\n", stdToken, tcToken, i);
			}
		}

		float errorRate = ((float) sz - match) / sz;
		System.out.println("Error Rate: " + errorRate);

		System.out.println("------ end evaluation output -----");
	}
}