package NER.name_entity;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.util.CoreMap;

public class DayCountExtractor {
    public static void main(String[] args) {
        String input = "Day 1-3: Rome, Italy\n"
        		+ "- Explore the Colosseum, Roman Forum, and Pantheon\n"
        		+ "- Visit the Vatican City and Sistine Chapel\n"
        		+ "- Enjoy Italian cuisine and relax in a piazza\n"
        		+ "\n"
        		+ "Day 4: Barcelona, Spain\n"
        		+ "- Fly to Barcelona and visit Antoni Gaudi's Sagrada Familia and Park Guell\n"
        		+ "- Explore the Gothic Quarter and La Rambla\n"
        		+ "- Try traditional tapas and paella\n"
        		+ "\n"
        		+ "Day 5: Madrid, Spain\n"
        		+ "- Fly to Madrid and visit the Prado Museum and Retiro Park\n"
        		+ "- Explore the Royal Palace and Plaza Mayor\n"
        		+ "- Try churros and chocolate\n"
        		+ "\n"
        		+ "Day 6: Lisbon, Portugal\n"
        		+ "- Fly to Lisbon and visit Belem Tower and Jeronimos Monastery\n"
        		+ "- Explore Alfama district and try pasteis de nata\n"
        		+ "- Relax at Praia da Rocha or Carcavelos Beach\n"
        		+ "\n"
        		+ "Day 7: Amsterdam, Netherlands\n"
        		+ "- Fly to Amsterdam and visit the Van Gogh  and Anne Frank House\n"
        		+ "- Explore the canals and Vondelpark\n"
        		+ "- Try herring and stroopwafels\n"
        		+ "\n"
        		+ "Day 8: Berlin, Germany\n"
        		+ "- Fly to Berlin and visit the Berlin Wall Memorial and Checkpoint Charlie\n"
        		+ "- Explore Museum Island and the Brandenburg Gate\n"
        		+ "- Try currywurst and beer\n"
        		+ "\n"
        		+ "Day 9: Paris, France\n"
        		+ "- Fly to Paris and visit the Louvre Museum and Eiffel Tower\n"
        		+ "- Explore Montmartre and Sacre-Coeur\n"
        		+ "- Try croissants and escargot\n"
        		+ "\n"
        		+ "Day 10: Vienna, Austria\n"
        		+ "- Fly to Vienna and visit Schonbrunn Palace and Belvedere Palace\n"
        		+ "- Explore the Historic Center of Vienna and St. Stephen's Cathedral\n"
        		+ "- Try Sachertorte and coffee\n"
        		+ "\n"
        		+ "Day 11: London, United Kingdom\n"
        		+ "- Fly to London and visit the British Museum and Tower of London\n"
        		+ "- Explore Buckingham Palace and Hyde \n"
        		+ "- Try fish and chips and a pint of ale\n";
        		
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        
        // Specify the path to your local classifier
        props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");
        
        StanfordCoreNLP pipeline = new StanfordCoreNLP();

        Annotation annotation = new Annotation(input);
        pipeline.annotate(annotation);
        pipeline.addAnnotator(new TokenizerAnnotator(false));
        pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
        pipeline.addAnnotator(new POSTaggerAnnotator(false));
        pipeline.addAnnotator(new TimeAnnotator("sutime", props));
        

        for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                if (token.get(CoreAnnotations.PartOfSpeechAnnotation.class).startsWith("CD")) {
                    // CD is the part-of-speech tag for cardinal numbers
                    String dayCountText = token.word();
                    int dayCount = Integer.parseInt(dayCountText);
                    System.out.println("Day count: " + dayCount);
                }
            }
        }
    }
}