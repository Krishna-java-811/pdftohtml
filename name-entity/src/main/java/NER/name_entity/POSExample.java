package NER.name_entity;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import java.util.Properties;

public class POSExample {
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
    public static void main(String[] args) {

    	Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        props.setProperty("ner.docdate.usePresent", "true");
        props.setProperty("sutime.includeRange", "true");
        props.setProperty("sutime.markTimeRanges", "true");
        
        // Load custom models if necessary
        props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
        props.setProperty("ner.model", "/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");

        // Create CoreNLP pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

      

        CoreDocument coreDocument = new CoreDocument(text);

        pipeline.annotate(coreDocument);

        List<CoreLabel> coreLabelList = coreDocument.tokens();

        for(CoreLabel coreLabel : coreLabelList) {

            String pos = coreLabel.get(CoreAnnotations.PartOfSpeechAnnotation.class);
            System.out.println(coreLabel.originalText() + " = "+ pos);
        }


    }
}
