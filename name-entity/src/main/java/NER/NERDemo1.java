package NER;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class NERDemo1 {
	static String text1 = "can you suggest restaurants near by burj khalifa";
	public static void main(String[] args) throws Exception {
		Long startTime = System.currentTimeMillis();

		// String serializedClassifier =
		// "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.all.3class.distsim.crf.ser.gz";

		String serializedClassifier = "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz";
		// String serializedClassifier =
		// "/Users/krishnak/Downloads/stanford-ner-2020-11-17/classifiers/english.muc.7class.distsim.crf.ser.gz";

		AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

//      String[] example = {"Good afternoon Rajat Raina, how are you today?",
//                          "I go to school at Stanford University, which is located in California." };
		String text =  "Sure, here are some restaurant recommendations for your 10-day trip:\n"
				+ "\n"
				+ "* Al Reef Restaurant: For authentic Emirati cuisine with stunning city views.\n"
				+ "* The Dubai Fish Market: A vibrant seafood market with a wide selection of fresh seafood and traditional dishes.\n"
				+ "* Al Hadheerah: A traditional Arabian restaurant with a warm atmosphere and delicious food.\n"
				+ "* The Social House: A stylish restaurant with a modern take on traditional Emirati dishes.\n"
				+ "* The Gallery Cafe: A chic restaurant with a terrace overlooking the Dubai Marina.\n"
				+ "* The Bombay Bungalow: A popular spot for both locals and tourists, serving authentic Indian cuisine.\n"
				+ "* Zaatar Restaurant: A casual restaurant with a cozy atmosphere and a wide selection of Middle Eastern dishes.\n"
				+ "* The Secret Garden: A hidden oasis in the heart of the city, serving organic and healthy cuisine.\n"
				+ "* The Butcher's Block: A popular restaurant with a wide selection of meats, seafood, and vegetarian options.";


		// Split the string by newline characters
		String[] splitText = text.split("\n");

//			for (String str : splitText) {
//				System.out.println(classifier.classifyToString(str));
//			}
//		System.out.println("---");
//
//      for (String str : splitText) {
//        // This one puts in spaces and newlines between tokens, so just print not println.
//        System.out.print(classifier.classifyToString(str, "slashTags", false));
//      }
//      System.out.println("slashTags ---");

//      for (String str : splitText) {
//        // This one is best for dealing with the output as a TSV (tab-separated column) file.
//        // The first column gives entities, the second their classes, and the third the remaining text in a document
//        System.out.print(classifier.classifyToString(str, "tabbedEntities", false));
//      }
//      System.out.println(" tabbedEntities ---");
//			List<String> persons = new ArrayList<String>();
//			List<String> locations = new ArrayList<String>();
////			// Loop through each line and classify
//			for (String str : splitText) {
//				// This returns entities in a tab-separated format
//				String classifiedStr = classifier.classifyToString(str, "tabbedEntities", false);
//				// System.out.println("Classified String: " + classifiedStr); // Debugging line
//
//				// Split the classified string into lines
//				String[] lines = classifiedStr.split("\\r?\\n");
////
//				// Iterate through each line
//				for (String line : lines) {
//					// Split each line by tabs to get entity and type
//					String[] parts = line.split("\t");
//
//					// Check if the line has location or person type
//					if (parts.length > 1 && (parts[1].equals("LOCATION"))) {
//						String entity = parts[0];
//						
//						locations.add(entity);
//						// System.out.println("Entity: " + entity + ", Type: " + type);
//					} else if ((parts.length > 1) && (parts[1].equals("PERSON"))) {
//						String entity = parts[0];
//						
//						persons.add(entity);
//					}
//				}
//			}
//			
//			System.out.println("Locations :" + locations);
//			System.out.println("Person :" + persons);
//
//			System.out.println("---");

//
//			for (String str : splitText) {
//				StringBuilder filteredText = new StringBuilder();
//				String xmlOutput = classifier.classifyWithInlineXML(str);
//				System.out.println(xmlOutput);
//
//				String[] tokens = xmlOutput.split(" ");
//				for (String token : tokens) {
//					if (token.contains("<LOCATION>")) {
//						String location = token.replaceAll("<(/)?[A-Z]+>", "").trim();
//						if (!location.isEmpty()) {
//							filteredText.append(location).append(" ");
//						}
//					} else if (token.contains("<PERSON>")) {
//						String person = token.replaceAll("<(/)?[A-Z]+>", "").trim();
//						if (!person.isEmpty()) {
//							filteredText.append(person).append(" ");
//						}
//					}
//				}
//				System.out.println(filteredText.toString());
//			}
//			System.out.println("---");

//      for (String str : splitText) {
//        System.out.println(classifier.classifyToString(str, "xml", true));
//      }
//      System.out.println("---");

//      for (String str : splitText) {
//        System.out.print(classifier.classifyToString(str, "tsv", false));
//      }
//      System.out.println("---");

		// This gets out entities with character offsets
//      int j = 0;
//      for (String str : splitText) {
//        j++;
//        List<Triple<String,Integer,Integer>> triples = classifier.classifyToCharacterOffsets(str);
//        for (Triple<String,Integer,Integer> trip : triples) {
//          System.out.printf("%s over character offsets [%d, %d) in sentence %d.%n",
//                  trip.first(), trip.second(), trip.third, j);
//        }
//      }
//      System.out.println("---");
//
		// This prints out all the details of what is stored for each token
//      int i=0;
//      for (String str : splitText) {
//        for (List<CoreLabel> lcl : classifier.classify(str)) {
//          for (CoreLabel cl : lcl) {
//            System.out.print(i++ + ": ");
//            System.out.println(cl.toShorterString());
//          }
//        }
//      }
//
//      System.out.println("---");
//			
//			 for (String str : splitText) {
//	                String outPut=classifier.classifyToString(str);
//	                System.out.println(outPut);
//	            }
//	            System.out.println("---");
//
//	            for (String str : splitText) {
//	                String outPut=classifier.classifyToString(str, "slashTags", false);
//	                // This one puts in spaces and newlines between tokens, so just print not println.
//	                System.out.print(classifier.classifyToString(str, "slashTags", false));
//	            }
//	            System.out.println("---");

		List<String> entities = new ArrayList<>();
		int count = 0;
		for (String str : splitText) {
			// String outPut=classifier.classifyToString(str, "tabbedEntities", false);
			// This one is best for dealing with the output as a TSV (tab-separated column)
			// file.
			// The first column gives entities, the second their classes, and the third the
			// remaining text in a document

			// This returns entities in a tab-separated format
			String classifiedStr = classifier.classifyToString(str, "tabbedEntities", false);
			// Split the classified string into lines
			String[] lines = classifiedStr.split("\\r?\\n");

			// Iterate through each line
			for (String line : lines) {
				// Split each line by tabs to get entity and type
				String[] parts = line.split("\t");
				// Check if the line has location or person type
				if (parts.length > 1 && ((parts[1].equals("LOCATION")) || (parts[1].equals("ORGANIZATION"))
						|| (parts[1].equals("PERSON")) || (parts[1].equals("MISC")))) {
					String entity = parts[0];
					count++;
					entities.add(count + ") " + entity + "----- Entity Type " + parts[1]);

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
