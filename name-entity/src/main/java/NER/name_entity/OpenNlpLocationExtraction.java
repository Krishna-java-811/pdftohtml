package NER.name_entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import opennlp.tools.namefind.*;
import opennlp.tools.tokenize.*;
import opennlp.tools.util.Span;

public class OpenNlpLocationExtraction {

	public static void main(String[] args) throws Exception {
		String filePath = "/Users/krishnak/Downloads/CMPL - UK Partner Universities 2021.pdf";
		

		try (PDDocument document = PDDocument.load(new File(filePath))) {
			PDFTextStripper pdfStripper = new PDFTextStripper();
			String text = pdfStripper.getText(document);

			// Split text into lines and process
			String[] lines = text.split("\n");
			// Initialize the tokenizer
			try (InputStream tokenizerModelIn = new FileInputStream(
					"/Users/krishnak/Documents/open_nlp_models/en-token.bin")) {
				TokenizerModel tokenizerModel = new TokenizerModel(tokenizerModelIn);
				TokenizerME tokenizer = new TokenizerME(tokenizerModel);

				// Define the paths for the models
				String[] modelPaths = { "/Users/krishnak/Documents/open_nlp_models/en-ner-location.bin",
						"/Users/krishnak/Documents/open_nlp_models/en-ner-person.bin",
						"/Users/krishnak/Documents/open_nlp_models/en-ner-organization.bin" };

				// Load the models using the utility method
				TokenNameFinderModel[] models = NlpUtils.loadModels(modelPaths);

				// Extract entities for each line of the text
				String[] splitText = text.split("\n");
				for (String line : splitText) {
					String[] tokens = tokenizer.tokenize(line);
					NlpUtils.extractEntities(models, tokens);
				}

				System.out.println("Entity extraction completed");
			}
		}
	}
}

class NlpUtils {

	/**
	 * Utility method to load OpenNLP models.
	 *
	 * @param modelPaths Array of paths to the models.
	 * @return Array of TokenNameFinderModel instances.
	 * @throws Exception if an error occurs while loading the models.
	 */
	public static TokenNameFinderModel[] loadModels(String[] modelPaths) throws Exception {
		TokenNameFinderModel[] models = new TokenNameFinderModel[modelPaths.length];
		for (int i = 0; i < modelPaths.length; i++) {
			try (InputStream modelIn = new FileInputStream(modelPaths[i])) {
				models[i] = new TokenNameFinderModel(modelIn);
			}
		}
		return models;
	}

	/**
	 * Utility method to extract and print entities using the provided models.
	 *
	 * @param models Array of TokenNameFinderModel instances.
	 * @param tokens Array of tokens for which entities should be extracted.
	 */
	public static void extractEntities(TokenNameFinderModel[] models, String[] tokens) {
		for (TokenNameFinderModel model : models) {
			NameFinderME nameFinder = new NameFinderME(model);
			Span[] nameSpans = nameFinder.find(tokens);

			for (Span span : nameSpans) {
				String entity = String.join(" ", Arrays.copyOfRange(tokens, span.getStart(), span.getEnd()));
				System.out.println(span.getType() + ": " + entity);
			}
		}
	}
}
