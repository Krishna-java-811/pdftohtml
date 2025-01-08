package NER;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreEntityMention;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;
import org.simmetrics.builders.StringDistanceBuilder;
import org.simmetrics.builders.StringMetricBuilder;
import org.simmetrics.metrics.EuclideanDistance;
import org.simmetrics.metrics.Levenshtein;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;

import com.google.common.base.Predicates;

public class CosineSimilarityExample {

    public static void main(String[] args) {
    	
    	// Define your documents
        String str1 = "Burj Al-Arab Jumeirah";
        String str2 = "Burj Al Arab";
    	  
        StringMetric metric = StringMetricBuilder
				.with(new org.simmetrics.metrics.CosineSimilarity<String>())
				   .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
		            .simplify(Simplifiers.replaceNonWord())
		            .simplify(Simplifiers.removeDiacritics())
				.tokenize(Tokenizers.whitespace())
				.build();
       
        StringMetric metric1 = StringMetricBuilder
				. with(new Levenshtein())
				 .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
		            .simplify(Simplifiers.replaceNonWord())
		            .simplify(Simplifiers.removeDiacritics())
				//.tokenize(Tokenizers.whitespace())
				.build();
        StringDistance metric2 = StringDistanceBuilder
				.with(new EuclideanDistance<String>())
				.simplify(Simplifiers.toLowerCase())
				.simplify(Simplifiers.removeNonWord())
				.simplify(Simplifiers.removeDiacritics())
				.tokenize(Tokenizers.whitespace())
				
				.tokenize(Tokenizers.qGram(3))
				.build();

        
       

		float result = metric.compare(str1, str2); 
		 System.out.println("Cosine Similarity1: " + result);
		 
		 float result1 = metric1.compare(str1, str2); 
		 System.out.println("Levenshtein Similarity1: " + result1);
		 
		 float result2 = metric2.distance(str1, str2); 
		 System.out.println("EuclideanDistance Similarity1: " + result2);
    	
//    	Properties props = new Properties();
//         props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
//         // Specify the path to the model files
//         props.setProperty("pos.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english-left3words-distsim.tagger");
//         props.setProperty("ner.model", "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz");
//        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
//
//       
//
//        // Create CoreDocument objects
//        CoreDocument doc1 = new CoreDocument(text1);
//        CoreDocument doc2 = new CoreDocument(text2);
//
//        // Annotate the documents
//        pipeline.annotate(doc1);
//        pipeline.annotate(doc2);
//
//        // Get the entity mentions
//        List<CoreEntityMention> entitiesDoc1 = doc1.entityMentions();
//        List<CoreEntityMention> entitiesDoc2 = doc2.entityMentions();
//
//        // Print entities for debugging
//        System.out.println("Entities in text1:");
//        for (CoreEntityMention em : entitiesDoc1) {
//            System.out.println(em.text());
//        }
//
//        System.out.println("Entities in text2:");
//        for (CoreEntityMention em : entitiesDoc2) {
//            System.out.println(em.text());
//        }
//
//        // Calculate the Cosine Similarity only if entities are found
//        if (!entitiesDoc1.isEmpty() && !entitiesDoc2.isEmpty()) {
//            double dotProduct = 0.0;
//            double normA = 0.0;
//            double normB = 0.0;
//
//            for (CoreEntityMention em1 : entitiesDoc1) {
//                for (CoreEntityMention em2 : entitiesDoc2) {
//                    dotProduct += em1.text().length() * em2.text().length();
//                    normA += Math.pow(em1.text().length(), 2);
//                    normB += Math.pow(em2.text().length(), 2);
//                }
//            }
//
//            double cosineSimilarity = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));
//            System.out.println("Cosine Similarity: " + cosineSimilarity);
//        } else {
//            System.out.println("No named entities found in one or both texts. Cosine Similarity cannot be calculated.");
//        }
    }
}