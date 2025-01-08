package NER;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.text.similarity.LevenshteinDistance;
import org.json.JSONArray;
import org.json.JSONObject;

import me.xdrop.fuzzywuzzy.FuzzySearch;

public class FuzzyWuzzyExample {
	 public static void main(String[] args) {
	        String text1 = "Burj Al Arab".toLowerCase(); // Normalize case
	        String filePath = "/Users/krishnak/Documents/pdf/travllr-dev.attractions_names.json";
	        int highestSimilarity = 0;
	        String bestMatch = null;
	        int similarityThreshold = 70;  // Set the threshold for an "almost match"

	        try {
	            // Read the JSON file content
	            String content = new String(Files.readAllBytes(Paths.get(filePath)));
	            // Parse the JSON content
	            JSONArray jsonArray = new JSONArray(content);

	            // Iterate over the array and extract data
	            for (int r = 0; r < jsonArray.length(); r++) {
	                JSONObject datajsonObject = jsonArray.getJSONObject(r);
	                String name = datajsonObject.getString("name").toLowerCase(); // Normalize case

	                String cleanedName = name.replaceAll("\\(.*?\\)", "") // Remove text within parentheses
	                       .replaceAll("[^a-zA-Z0-9\\s]", " ") // Remove special characters
	                        .trim();

	                String target = text1.replaceAll("\\(.*?\\)", "") // Remove text within parentheses
	                        .replaceAll("[^a-zA-Z0-9\\s]", " ") // Remove special characters
	                        .trim();

	                System.out.println("Cleaned Name: " + cleanedName);

	                // Calculate the FuzzyWuzzy similarity
	                int similarity = FuzzySearch.ratio(cleanedName, target);

	                if (similarity > highestSimilarity) {
	                    highestSimilarity = similarity;
	                    bestMatch = name;
	                }
	            }

	            // Check if the best match is above the threshold
	            if (highestSimilarity >= similarityThreshold) {
	                System.out.println("Best match: " + bestMatch + " - FuzzyWuzzy Similarity: " + highestSimilarity);
	            } else {
	                System.out.println("No matching attraction found.");
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}