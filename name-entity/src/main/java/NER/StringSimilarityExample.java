package NER;

import static org.simmetrics.builders.StringMetricBuilder.with;

import java.util.Locale;

import org.apache.commons.text.similarity.CosineSimilarity;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LevenshteinDistance;
import info.debatty.java.stringsimilarity.Jaccard;
import org.simmetrics.StringMetric;
import org.simmetrics.builders.StringMetricBuilder;
import org.simmetrics.metrics.Levenshtein;
import org.simmetrics.metrics.StringMetrics;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Multiset;

public class StringSimilarityExample {
    public static void main(String[] args) {
        String str1 = "Desert Safari";
        String str2 = "Desert Safari Dubai";

        // Jaccard Index using bigrams
        Jaccard jaccard = new Jaccard(2);
        double jaccardIndex = jaccard.similarity(str1, str2);
        System.out.println("Jaccard Index: " + jaccardIndex);

        // Jaro-Winkler Distance
        JaroWinklerDistance jaroWinkler = new JaroWinklerDistance();
        double jaroWinklerSimilarity = jaroWinkler.apply(str1, str2);
        System.out.println("Jaro-Winkler Similarity: " + jaroWinklerSimilarity);

        // Levenshtein Distance
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        int levenshteinDistance = levenshtein.apply(str1, str2);
        System.out.println("Levenshtein Distance: " + levenshteinDistance);

        // Damerau-Levenshtein Distance
        LevenshteinDistance damerauLevenshtein = LevenshteinDistance.getDefaultInstance();
        int damerauLevenshteinDistance = damerauLevenshtein.apply(str1, str2);
        System.out.println("Damerau-Levenshtein Distance: " + damerauLevenshteinDistance);

        // Jaro Distance (using Jaro-Winkler without the Winkler adjustment)
        double jaroDistance = jaroWinkler.apply(str1, str2);
        System.out.println("Jaro Distance: " + jaroDistance);

        // Cosine Similarity
        StringMetric cosine = StringMetrics.cosineSimilarity();
        float cosineSimilarity = cosine.compare(str1, str2);
        System.out.println("Cosine Similarity: " + cosineSimilarity);
       
        StringMetric metric = StringMetricBuilder
				.with(new org.simmetrics.metrics.CosineSimilarity<String>())
				   .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
		            .simplify(Simplifiers.replaceNonWord())
		            .simplify(Simplifiers.removeDiacritics())
				.tokenize(Tokenizers.whitespace()).build();
       
        StringMetric metric1 = StringMetricBuilder
				. with(new Levenshtein())
				   .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
		            .simplify(Simplifiers.replaceNonWord())
		            .simplify(Simplifiers.removeDiacritics())
				.build();
        
       

		float result = metric.compare(str1, str2); 
		 System.out.println("Cosine Similarity1: " + result);
		 
		 float result1 = metric1.compare(str1, str2); 
		 System.out.println("Levenshtein Similarity1: " + result1);
		 
			Cache<String,String> stringCache = 
					CacheBuilder.newBuilder()
					.maximumSize(2)
					.build();
			
			Cache<String,Multiset<String>> tokenCache = 
					CacheBuilder.newBuilder()
					.maximumSize(2)
					.build();	
//			
//			StringMetric metric1 = 
//					with(new org.simmetrics.metrics.CosineSimilarity<String>())
//					.simplify(Simplifiers.toLowerCase())
//					.simplify(Simplifiers.removeNonWord())
//					.cacheStrings(stringCache)
//					.tokenize(Tokenizers.qGram(3))
//					.cacheTokens(tokenCache)
//					.build();
//
//			float result1 = metric1.compare(str1, str2); 
//			 System.out.println("Cosine Similarity2: " + result1);
		// 0.5720
        // Jaccard Similarity (using Simmetrics)
        StringMetric jaccardSimmetrics = StringMetrics.jaccard();
        float jaccardSimmetricsSimilarity = jaccardSimmetrics.compare(str1, str2);
        System.out.println("Jaccard Similarity (Simmetrics): " + jaccardSimmetricsSimilarity);

        // Euclidean Distance
        StringMetric euclidean = StringMetrics.euclideanDistance();
        float euclideanDistance = euclidean.compare(str1, str2);
        System.out.println("Euclidean Distance: " + euclideanDistance);

        // Smith-Waterman
        StringMetric smithWaterman = StringMetrics.smithWatermanGotoh();
        float smithWatermanSimilarity = smithWaterman.compare(str1, str2);
        System.out.println("Smith-Waterman Similarity: " + smithWatermanSimilarity);
    }
}
