package NER;

import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class SimpleCoreNLPDemo {
    // Input text
    static String text = "Day 1\n" + "\n"
            + "*Morning Visit the Gold Souk for traditional jewelry, spices, and textiles.\n"
            + "*Afternoon Explore the Dubai Fountains Show a spectacular water, light, and music spectacle.\n"
            + "*Evening Enjoy a sunset dinner at a rooftop restaurant with stunning city views.\n" + "\n"
            + "Day 2\n" + "\n"
            + "*Morning Visit the Dubai Miracle Garden for a surreal display of flowers and plants.\n"
            + "*Afternoon Take a thrilling desert safari with dune bashing, sandboarding, and a traditional Bedouin dinner.\n"
            + "*Evening Experience the vibrant nightlife of Dubai Marina with its bars, clubs, and live entertainment.\n"
            + "\n" + "Day 3\n" + "\n"
            + "*Morning Explore the Dubai Museum to learn about the city's history and culture.\n"
            + "*Afternoon Visit the Dubai Frame a unique structure offering panoramic views of the city.\n"
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

    public static void main(String[] args) throws ClassCastException, ClassNotFoundException, IOException {
        // Setup Stanford CoreNLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
       
        // Initialize StanfordCoreNLP with the specified properties
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Define the path to the serialized NER model
        String serializedClassifier = "edu/stanford/nlp/models/ner/english.muc.7class.distsim.crf.ser.gz";

        // Initialize the classifier
        AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

        // Create annotation
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        // Extract sentences
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);

        // Process each sentence and print TSV output
        for (CoreMap sentence : sentences) {
            // Extract the sentence text
            String sentenceText = sentence.toString();

            // Get the TSV formatted output
            String tsvOutput = classifier.classifyToString(sentenceText, "tabbedEntities", false);

            // Print the TSV formatted output
            System.out.print(tsvOutput);
        }
    }
}
