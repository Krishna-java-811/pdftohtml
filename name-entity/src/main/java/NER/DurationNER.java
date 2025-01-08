package NER;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
//import edu.stanford.nlp.pipeline.*;
//import edu.stanford.nlp.ling.*;
//import edu.stanford.nlp.util.*;
import edu.stanford.nlp.simple.*;
import edu.stanford.nlp.pipeline.*;

import java.util.Properties;
import java.util.stream.Collectors;

import java.util.*;

public class DurationNER {
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

    public static void main(String[] args) {
    	Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner"); // Exclude SUTime

		// Specify the path to the model files
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model","/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
         
         // set up pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        // make an example document
        CoreDocument doc = new CoreDocument(text);
        // annotate the document
        pipeline.annotate(doc);
        // view results
        System.out.println("---");
     // Split the string by newline characters
     		String[] splitText = text.split("\n");
       for(String s:splitText) {
    	   Sentence sent = new Sentence(s);
           List<String> nerTags = sent.nerTags();  // [PERSON, O, O, O, O, O, O, O]
           System.out.println(nerTags);
       }
      
//        
//        //        System.out.println("entities found");
//        for (CoreEntityMention em : doc.entityMentions()) {
//          System.out.println("\tdetected entity: \t"+em.text()+"\t"+em.entityType());
//        }
//        System.out.println("---");
//        System.out.println("tokens and ner tags");
//        String tokensAndNERTags = doc.tokens().stream().map(token -> "("+token.word()+","+token.ner()+")").collect(
//            Collectors.joining(" "));
//        System.out.println(tokensAndNERTags);
    }
}
