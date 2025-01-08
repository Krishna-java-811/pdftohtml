package NER;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Multiset;

import org.simmetrics.SetMetric;
import org.simmetrics.StringDistance;
import org.simmetrics.StringMetric;
import org.simmetrics.builders.StringMetricBuilder;
import org.simmetrics.builders.StringDistanceBuilder;
import org.simmetrics.metrics.CosineSimilarity;
import org.simmetrics.metrics.EuclideanDistance;
import org.simmetrics.metrics.Levenshtein;
import org.simmetrics.metrics.OverlapCoefficient;
import org.simmetrics.simplifiers.Simplifier;
import org.simmetrics.simplifiers.Simplifiers;
import org.simmetrics.tokenizers.Tokenizers;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;

import static java.util.Arrays.asList;

public class StringMetricExamples {

    public static void main(String[] args) {
        printAllExamples();
    }

    public static void printAllExamples() {
    	  String str1 = "Desert Safari";
          String str2 = "Desert Safari Dubai";

        System.out.println("CosineSimilarity: " + example02(str1, str2));
        System.out.println("EuclideanDistance: " + example03(str1, str2));
        System.out.println("OverlapCoefficient: " + example04(str1, str2));
        System.out.println("Levenshtein: " + example00(str1, str2));
        System.out.println("StringDistanceBuilder Levenshtein: " + example01_v2(str1, str2));
        System.out.println("StringDistanceBuilder Levenshtein_1: " + example02_v2(str1, str2));
        System.out.println("EuclideanDistance_1: " + example03_v2(str1, str2));
        System.out.println("EuclideanDistance_2: " + example04_v2(str1, str2));
        System.out.println("EuclideanDistance_3: " + example07(str1, str2));
        System.out.println("Levenshtein_2: " + example01_v3(str1, str2));
        System.out.println("EuclideanDistance_4: " + example03_v3(str1, str2));
    }



    public static float example02(String str1, String str2) {
        StringMetric metric = StringMetricBuilder
                .with(new CosineSimilarity<String>())
                .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
                .simplify(Simplifiers.replaceNonWord())
                .tokenize(Tokenizers.whitespace()).build();
        return metric.compare(str1, str2);
    }

    public static float example03(String str1, String str2) {
        StringDistance metric = StringDistanceBuilder
                .with(new EuclideanDistance<String>())
                .simplify(Simplifiers.toLowerCase(Locale.ENGLISH))
                .simplify(Simplifiers.replaceNonWord())
                .tokenize(Tokenizers.whitespace()).build();
        return metric.distance(str1, str2);
    }

    public static float example04(String str1, String str2) { 
        Set<Integer> scores1 = new HashSet<>(asList(1, 1, 2, 3, 5, 8, 11, 19));
        Set<Integer> scores2 = new HashSet<>(asList(1, 2, 4, 8, 16, 32, 64));
        SetMetric<Integer> metric = new OverlapCoefficient<>();
        return metric.compare(scores1, scores2);
    }

    public static float example00(String str1, String str2) {
       StringDistance metric = new Levenshtein();
        return metric.distance(str1, str2);
    }

    public static float example01_v2(String str1, String str2) {
       
        StringDistance metric = StringDistanceBuilder
                .with(new Levenshtein())
                .simplify(Simplifiers.removeDiacritics())
                .build();
        return metric.distance(str1, str2);
    }

    public static float example02_v2(String str1, String str2) {
        StringDistance metric = StringDistanceBuilder
                .with(new Levenshtein())
                .simplify(Simplifiers.removeDiacritics())
                .simplify(Simplifiers.toLowerCase())
                .build();
        return metric.distance(str1, str2);
    }

    public static float example03_v2(String str1, String str2) {
       
        StringDistance metric = StringDistanceBuilder
                .with(new EuclideanDistance<String>())
                .tokenize(Tokenizers.whitespace())
                .build();
        return metric.distance(str1, str2);
    }

    public static float example04_v2(String str1, String str2) {
     
        StringDistance metric = StringDistanceBuilder
                .with(new EuclideanDistance<String>())
                .tokenize(Tokenizers.whitespace())
                .tokenize(Tokenizers.qGram(3))
                .build();
        return metric.distance(str1, str2);
    }


    public static float example07(String str1, String str2) {
       
        Cache<String, String> stringCache = CacheBuilder.newBuilder().maximumSize(2).build();
        Cache<String, Multiset<String>> tokenCache = CacheBuilder.newBuilder().maximumSize(2).build();
        StringDistance metric = StringDistanceBuilder
                .with(new EuclideanDistance<String>())
                .simplify(Simplifiers.toLowerCase())
                .simplify(Simplifiers.removeNonWord())
                .cacheStrings(stringCache)
                .tokenize(Tokenizers.qGram(3))
                .cacheTokens(tokenCache)
                .build();
        return metric.distance(str1, str2);
    }

    public static float example01_v3(String str1, String str2) {
        StringDistance metric = new Levenshtein();
        return metric.distance(str1, str2);
    }


    public static float example03_v3(String str1, String str2) {
       
        StringDistance metric = StringDistanceBuilder
                .with(new EuclideanDistance<String>())
                .tokenize(Tokenizers.qGram(3))
                .build();
        return metric.distance(str1, str2);
    }
}
