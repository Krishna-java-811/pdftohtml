package NER;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.json.JSONArray;
import org.json.JSONObject;

public class LevenshteinExample {
    public static void main(String[] args) {
        LevenshteinDistance levenshtein = new LevenshteinDistance();
        String text1 = "Burj Al Arab".toLowerCase(); // Normalize case
        String filePath = "/Users/krishnak/Documents/pdf/travllr-dev.attractions_names.json";
        double highestSimilarity = 0.0;
        String bestMatch = null;
        double similarityThreshold = 0.5;  // Set the threshold for an "almost match"

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

                System.out.println(cleanedName);

                // Calculate the Levenshtein Distance
                int distance = levenshtein.apply( cleanedName,text1);

                // Calculate the maximum possible distance
                int maxLength = Math.max(text1.length(), cleanedName.length());

                // Normalize the distance to a similarity score between 0 and 1
                double similarity = 1.0 - (double) distance / maxLength;
                if (similarity > highestSimilarity) {
                    highestSimilarity = similarity;
                    bestMatch = name;
                }
            }

            // Check if the best match is above the threshold
            if (highestSimilarity >= similarityThreshold) {
                System.out.println("Best match: " + bestMatch + " - Levenshtein Similarity: " + highestSimilarity);
            } else {
                System.out.println("No matching attraction found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
