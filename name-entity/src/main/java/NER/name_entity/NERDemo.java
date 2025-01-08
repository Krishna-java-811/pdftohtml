package NER.name_entity;

import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NERDemo {
	// static String text1 = "Can you suggest restaurants nearby Burj Khalifa ?";
	public static void main(String[] args) throws Exception {
		Long startTime = System.currentTimeMillis();

		// String serializedClassifier =
		// "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.all.3class.distsim.crf.ser.gz";

		//String serializedClassifier = "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.conll.4class.distsim.crf.ser.gz";
		 String serializedClassifier =
		 "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz";

		 
		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

//      String[] example = {"Good afternoon Rajat Raina, how are you today?",
//                          "I go to school at Stanford University, which is located in California." };

        String text = "8 days trip in dubai";


		// Split the string by newline characters
	String[] splitText = text.split("\n");
//		  for (String str : splitText) {
//		        // This one puts in spaces and newlines between tokens, so just print not println.
//		        System.out.print(classifier.classifyToString(str, "slashTags", false));
//		      }
//
		List<String> entities = new ArrayList<>();
		
		
		
		int count = 0;
		for (String str : splitText) {
			// String outPut=classifier.classifyToString(str, "tabbedEntities", false);
			// This one is best for dealing with the output as a TSV (tab-separated column)
			// file.
			// The first column gives entities, the second their classes, and the third the
			// remaining text in a document

			// This returns entities in a tab-separated format
			String classifiedStr = classifier.classifyToString(str.trim(), "tabbedEntities", false);
			// Split the classified string into lines
			String[] lines = classifiedStr.split("\\r?\\n");

			//|| (parts[1].equals("DATE")
			// Iterate through each line
			for (String line : lines) {
				// Split each line by tabs to get entity and type
				String[] parts = line.split("\t");
				// Check if the line has location or person type
				if (parts.length > 1 && ((parts[1].equals("LOCATION")) || (parts[1].equals("ORGANIZATION"))
						|| (parts[1].equals("PERSON")) || (parts[1].equals("MISC"))|| (parts[1].equals("DATE"))||
						(parts[1].equals("TIME"))
						)) {
					String entity = parts[0];
					count++;
					entities.add(parts[1].toLowerCase() +": " +entity);
				
					// System.out.println(count + " )" + entity + " Entity Type " + parts[1]);
				}
			}
			// System.out.print(classifier.classifyToString(str, "tabbedEntities", false));
		}
		// Print all entities in the same line, separated by commas
		for (int i = 0; i < entities.size(); i++) {

			System.out.println(entities.get(i));
		}
		System.out.println("---");


		Long endTime = System.currentTimeMillis();
		Long executionTime = endTime - startTime; // This is in milliseconds
		double executionTimeInSeconds = executionTime / 1000.0; // Convert to seconds
		System.out.println("The model execution time is : " + executionTimeInSeconds + " seconds");
	}
}
