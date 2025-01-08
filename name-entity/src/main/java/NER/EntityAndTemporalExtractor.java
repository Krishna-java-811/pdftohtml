package NER;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.time.SUTime;
import edu.stanford.nlp.time.SUTime.Temporal;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EntityAndTemporalExtractor {

    private static AnnotationPipeline suTimePipeline = null;
    private static StanfordCoreNLP nerPipeline = null;

    static {
        // Initialize SUTime Annotation Pipeline
        try {
        	
            String defsSutime = "edu/stanford/nlp/models/sutime/defs.sutime.txt";
            String holidaySutime = "edu/stanford/nlp/models/sutime/english.holidays.sutime.txt";
            String sutime = "edu/stanford/nlp/models/sutime/english.sutime.txt";
            Properties props = new Properties();
            String sutimeRules = defsSutime + "," + holidaySutime + "," + sutime;
            props.setProperty("sutime.rules", sutimeRules);
            props.setProperty("sutime.binders", "0");
            props.setProperty("sutime.markTimeRanges", "true");
            props.setProperty("sutime.includeRange", "true");
            // Specify the path to the model files
            props.setProperty("pos.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english-left3words-distsim.tagger");
            props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");
            suTimePipeline = new AnnotationPipeline();
            suTimePipeline.addAnnotator(new TokenizerAnnotator(false));
            suTimePipeline.addAnnotator(new TimeAnnotator("sutime", props));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize Stanford NER Pipeline
        Properties nerProps = new Properties();
        nerProps.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
        nerPipeline = new StanfordCoreNLP(nerProps);
    }

    public static void annotateText(String text, String referenceDate) {
        try {
            // Set reference date
            if (referenceDate == null || referenceDate.isEmpty()) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                referenceDate = dateFormat.format(new Date());
            } else {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateFormat.parse(referenceDate);
                } catch (Exception e) {
                    referenceDate = dateFormat.format(new Date());
                }
            }

            // Annotation using SUTime for temporal expressions
            if (suTimePipeline != null) {
                Annotation annotation = new Annotation(text);
                annotation.set(CoreAnnotations.DocDateAnnotation.class, referenceDate);
                suTimePipeline.annotate(annotation);
                List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);

                for (CoreMap cm : timexAnnsAll) {
                    try {
                        List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
                        String startOffset = tokens.get(0).get(CoreAnnotations.CharacterOffsetBeginAnnotation.class).toString();
                        String endOffset = tokens.get(tokens.size() - 1).get(CoreAnnotations.CharacterOffsetEndAnnotation.class).toString();
                        Temporal temporal = cm.get(TimeExpression.Annotation.class).getTemporal();

                        System.out.println("Temporal Token text : " + cm.toString());
                        System.out.println("Temporal Value : " + temporal.toString());
                        System.out.println("Timex : " + temporal.getTimexValue());
                        System.out.println("Timex type : " + temporal.getTimexType().name());
                        System.out.println("Start offset : " + startOffset);
                        System.out.println("End Offset : " + endOffset);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("SUTime Pipeline object is NULL");
            }

            // Annotation using Stanford NER
            if (nerPipeline != null) {
                Annotation nerAnnotation = new Annotation(text);
                nerPipeline.annotate(nerAnnotation);
                List<CoreMap> sentences = nerAnnotation.get(CoreAnnotations.SentencesAnnotation.class);

                for (CoreMap sentence : sentences) {
                    for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                        String word = token.word();
                        String ne = token.get(NamedEntityTagAnnotation.class);
                        System.out.println("Word: " + word + " | Named Entity: " + ne);
                    }
                }
            } else {
                System.out.println("NER Pipeline object is NULL");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
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
                + "*Afternoon Visit the Dubai Frame a unique structure offering panoramic views of the city.\n"
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
        String referenceDate = null; // Use current date if null
        annotateText(text, referenceDate);
    }
}
