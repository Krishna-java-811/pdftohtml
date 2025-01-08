package NER.name_entity;

import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;

public class NERExample {

    public static void main(String[] args) throws Exception {
        // Load NER models
//        InputStream personModelIn = new FileInputStream("/Users/krishnak/Downloads/en-ner-person.bin");
//        TokenNameFinderModel personModel = new TokenNameFinderModel(personModelIn);
//        NameFinderME personFinder = new NameFinderME(personModel);

//        InputStream locationModelIn = new FileInputStream("/Users/krishnak/Downloads/en-ner-location.bin");
//        TokenNameFinderModel locationModel = new TokenNameFinderModel(locationModelIn);
//        NameFinderME locationFinder = new NameFinderME(locationModel);

        InputStream dateModelIn = new FileInputStream("/Users/krishnak/Downloads/en-ner-date.bin");
        TokenNameFinderModel dateModel = new TokenNameFinderModel(dateModelIn);
        NameFinderME dateFinder = new NameFinderME(dateModel);
        
        // Example text
        String text = "Day 1\n"
        		
        		+ "*Morning Visit the Gold Souk for traditional jewelry, spices, and textiles.\n"
        		+ "*Afternoon Explore the Dubai Fountains Show a spectacular water, light, and music spectacle.\n"
        		+ "*Evening Enjoy a sunset dinner at a rooftop restaurant with stunning city views.\n"
        		+ "\n"
        		+ "Day 2\n"
        		+ "\n"
        		+ "*Morning Visit the Dubai Miracle Garden for a surreal display of flowers and plants.\n"
        		+ "*Afternoon Take a thrilling desert safari with dune bashing, sandboarding, and a traditional Bedouin dinner.\n"
        		+ "*Evening Experience the vibrant nightlife of Dubai Marina with its bars, clubs, and live entertainment.\n"
        		+ "\n"
        		+ "Day 3\n"
        		+ "\n"
        		+ "*Morning Explore the Dubai Museum to learn about the city's history and culture.\n"
        		+ "*Afternoon Visit theDubai Frame a unique structure offering panoramic views of the city.\n"
        		+ "*Evening Enjoy a farewell dinner at a traditional Emirati restaurant.\n"
        		+ "\n"
        		+ "Additional recommendations\n"
        		+ "\n"
        		+ "*Stay at a beachfront hotel Enjoy the stunning views and amenities.\n"
        		+ "*Visit the Dubai Fountain and Dubai Marina at night Experience the illuminated spectacle.\n"
        		+ "*Take a day trip to Abu Dhabi Explore the Sheikh Zayed Grand Mosque and the Louvre Abu Dhabi.\n"
        		+ "*Try traditional Emirati dishes Such as shawarma, machboos, and dates.\n"
        		+ "*Explore the Dubai Desert Safari Park Enjoy an exciting adventure in the desert.\n"
        		+ "*Relax at Jumeirah Beach One of the most popular beaches in Dubai.\n"
        		+ "\n"
        		+ "Accommodation\n"
        		+ "\n"
        		+ "* The Atlantis The Palm\n"
        		+ "* Jumeirah Beach Hotel\n"
        		+ "* Burj Al Arab\n"
        		+ "* Emirates Palace Downtown\n"
        		+ "\n"
        		+ "Food and drinks\n"
        		+ "\n"
        		+ "* Al Hadheerah Restaurant (Emirati cuisine)\n"
        		+ "* The Globe (international cuisine)\n"
        		+ "* Pierchic (seafood)\n"
        		+ "* The View Restaurant (rooftop dining)\n"
        		+ "\n"
        		+ "Tips\n"
        		+ "\n"
        		+ "* Dress modestly when visiting religious sites.\n"
        		+ "* Use public transportation or taxis for transportation.\n"
        		+ "* Stay hydrated, especially in hot weather.\n"
        		+ "* Bargaining is expected at the Gold Souk and other markets.\n"
        		+ "* Learn a few basic Arabic phrases for a more immersive experience.";

        // Tokenize the text
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String[] tokens = tokenizer.tokenize(text);

        // Perform NER on tokens
       // Span[] nameSpansPersons = personFinder.find(tokens);
       // Span[] nameSpansLocations = locationFinder.find(tokens);
        Span[] dateSpansLocations = dateFinder.find(tokens);

        // Print entities found
//        System.out.println("Persons:");
//        for (Span s : nameSpansPersons) {
//            System.out.println(s.toString() + "  " + tokens[s.getStart()]);
//        }
//
//        System.out.println("\nLocations:");
//        for (Span s : nameSpansLocations) {
//            System.out.println(s.toString() + "  " + tokens[s.getStart()]);
//        }

        System.out.println("\nDate:");
        for (Span s : dateSpansLocations) {
            System.out.println(s.toString() + "  " + tokens[s.getStart()]);
        }
        
        // Close input streams
      //  personModelIn.close();
     //   locationModelIn.close();
        dateModelIn.close();
    }
}
