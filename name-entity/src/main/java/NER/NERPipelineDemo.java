package NER;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.util.CoreMap;

import java.util.Properties;
import java.util.stream.Collectors;

public class NERPipelineDemo {
	static String text =  "Day 1\n" + "\n"
			+ "*Morning Visit the Gold Souk for traditional jewelry, spices, and textiles.\n"
			+ "*Afternoon Explore the Dubai Fountains Show a spectacular water, light, and music spectacle.\n"
			+ "*Evening Enjoy a sunset dinner at a rooftop restaurant with stunning city views.\n" + "\n"
			+ "Day 2\n" + "\n"
			+ "*Morning Visit the Dubai Miracle Garden for a surreal display of flowers and plants.\n"
			+ "*Afternoon Take a thrilling desert safari with dune bashing, sandboarding, and a traditional Bedouin dinner.\n"
			+ "*Evening Experience the vibrant nightlife of Dubai Marina with its bars, clubs, and live entertainment.\n"
			+ "\n" + "Day 3\n" + "\n"
			+ "*Morning Explore the Dubai Museum to learn about the city's history and culture.\n"
			+ "*Afternoon Visit theDubai Frame a unique structure offering panoramic views of the city.\n"
			+ "*Evening Enjoy a farewell dinner at a traditional Emirati restaurant.\n" + "\n"
			+ "Additional recommendations\n" + "\n"
			+ "*Stay at a beachfront hotel Enjoy the stunning views and amenities.\n"
			+ "*Visit the Dubai Fountain and Dubai Marina at night Experience the illuminated spectacle.\n"
			+ "*Take a day trip to Abu Dhabi Explore the Sheikh Zayed Grand Mosque and the Louvre Abu Dhabi.\n"
			+ "*Try traditional Emirati dishes Such as shawarma, machboos, and dates.\n"
			+ "*Explore the Dubai Desert Safari Park Enjoy an exciting adventure in the desert.\n"
			+ "*Relax at Jumeirah Beach One of the most popular beaches in Dubai.\n" + "\n" + "Accommodation\n"
			+ "\n" + "* The Atlantis The Palm\n" + "* Jumeirah Beach Hotel\n" + "* Burj Al Arab\n"
			+ "* Emirates Palace Downtown\n" + "\n" + "Food and drinks\n" + "\n"
			+ "* Al Hadheerah Restaurant (Emirati cuisine)\n" + "* The Globe (international cuisine)\n"
			+ "* Pierchic (seafood)\n" + "* The View Restaurant (rooftop dining)\n" + "\n" + "Tips\n" + "\n"
			+ "* Dress modestly when visiting religious sites.\n"
			+ "* Use public transportation or taxis for transportation.\n"
			+ "* Stay hydrated, especially in hot weather.\n"
			+ "* Bargaining is expected at the Gold Souk and other markets.\n"
			+ "* Learn a few basic Arabic phrases for a more immersive experience.";
	static String text1 =  "Day 1-3: Paris, France \n"
			+ "\n"
			+ "* Explore the iconic Eiffel Tower, Louvre Museum, and Notre Dame Cathedral.\n"
			+ "* Take a stroll down the Seine River and visit the Palace of Versailles.\n"
			+ "* Enjoy a Parisian breakfast at a charming cafe.\n"
			+ "\n"
			+ " Day 4-6: Rome, Italy \n"
			+ "\n"
			+ "* Visit the Colosseum, Roman Forum, and Vatican City.\n"
			+ "* Marvel at the Trevi Fountain and toss a coin for good luck.\n"
			+ "* Explore the Vatican Museums and St. Peter's Basilica.\n"
			+ "\n"
			+ " Day 7: Florence, Italy \n"
			+ "\n"
			+ "* Immerse yourself in Renaissance art at the Uffizi Gallery and Ponte Vecchio.\n"
			+ "* Take a cooking class to learn Italian cuisine.\n"
			+ "* Visit the Duomo and climb to the top for panoramic views.\n"
			+ "\n"
			+ " Day 8-10: Barcelona, Spain \n"
			+ "\n"
			+ "* Explore Gaud??'s architectural wonders, including Park G??ell and Sagrada Familia.\n"
			+ "* Enjoy the vibrant atmosphere of Las Ramblas.\n"
			+ "* Take a day trip to Montserrat for stunning city views.\n"
			+ "\n"
			+ " Day 11-13: London, England \n"
			+ "\n"
			+ "* Visit Buckingham Palace, Tower of London, and the British Museum.\n"
			+ "* Take a stroll down the River Thames and visit the Houses of Parliament.\n"
			+ "* Explore the charming neighborhoods of London.\n"
			+ "\n"
			+ " Additional Recommendations: \n"
			+ "\n"
			+ "* Consider a trip to the Greek islands for stunning scenery and culture.\n"
			+ "* Explore Central Europe for a blend of history, art, and nature.\n"
			+ "* Visit Eastern Europe for a vibrant cultural and culinary experience.\n"
			+ "* Choose a destination based on your interests, such as history, art, or nightlife.\n"
			+ "\n"
			+ " Tips: \n"
			+ "\n"
			+ "* Book flights and accommodations in advance, especially during peak season.\n"
			+ "* Learn basic local phrases for easier communication.\n"
			+ "* Pack comfortable shoes for walking and exploring.\n"
			+ "* Respect local customs and traditions.";

	    public static void main(String[] args) {
	    	// Set up pipeline properties
	        Properties props = new Properties();
	        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");

	     // Specify the path to the models directory
	        props.setProperty("pos.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english-left3words-distsim.tagger");
	        props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");

	        // Include the regexner files for additional entity recognition
	        props.setProperty("ner.additional.regexner.mapping", 
	                "edu/stanford/nlp/models/kbp/english/gazetteers/regexner_cased.tab," +
	                "edu/stanford/nlp/models/kbp/english/gazetteers/regexner_caseless.tab");
	        props.setProperty("ner.additional.regexner.ignorecase", "true");

	        // Set up pipeline
	        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

	        // Make an example document without uppercasing the text
	        CoreDocument document = new CoreDocument(text1);
	        pipeline.annotate(document);

	        // Get confidences for entities
	        for (CoreEntityMention em : document.entityMentions()) {
	            System.out.println(em.text() + "\t" + em.entityType());
	        }
	        System.out.println("Completed");
	    }
}
