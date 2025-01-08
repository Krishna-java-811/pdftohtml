package NER;

import edu.stanford.nlp.pipeline.*;
import org.wikidata.wdtk.datamodel.helpers.Datamodel;
import org.wikidata.wdtk.datamodel.interfaces.ItemDocument;
import org.wikidata.wdtk.wikibaseapi.BasicApiConnection;
import org.wikidata.wdtk.wikibaseapi.WbSearchEntitiesResult;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class NerEntityExtractor {

	private StanfordCoreNLP pipeline;
	private WikibaseDataFetcher wbdf;

	public NerEntityExtractor() {
		// Initialize Stanford NLP pipeline with basic properties
		Properties props = new Properties();
		props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
		props.setProperty("ner.applyFineGrained", "false");
		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
		props.setProperty("ner.model",
				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
		pipeline = new StanfordCoreNLP(props);

		// Initialize Wikidata fetcher
		wbdf = new WikibaseDataFetcher(BasicApiConnection.getWikidataApiConnection(), Datamodel.SITE_WIKIDATA);
	}

	/**
	 * Extracts all named entities from the input text and adds descriptions.
	 *
	 * @param textToAnalyze The text to analyze.
	 * @return A list of named entities with descriptions.
	 */
	public List<NamedEntity> extractEntities(String textToAnalyze) {
		// Create a CoreDocument object
		CoreDocument doc = new CoreDocument(textToAnalyze);
		pipeline.annotate(doc);

		List<NamedEntity> namedEntities = new ArrayList<>();

		// Extract entities from the text
		for (CoreEntityMention em : doc.entityMentions()) {
			NamedEntity entity = new NamedEntity();
			entity.setText(em.text());
			entity.setType(em.entityType());
			addDescription(entity); // Add description from Wikidata
			namedEntities.add(entity);
		}

		return namedEntities;
	}

	/**
	 * Fetches a description for the named entity from Wikidata.
	 *
	 * @param entity The named entity.
	 */
	private void addDescription(NamedEntity entity) {
		try {
			// Search Wikidata for the entity
			List<WbSearchEntitiesResult> searchResults = wbdf.searchEntities(entity.getText());
			if (!searchResults.isEmpty()) {
				WbSearchEntitiesResult firstResult = searchResults.get(0);
				ItemDocument entityDoc = (ItemDocument) wbdf.getEntityDocument(firstResult.getTitle());

				// Get English description
				if (entityDoc.getDescriptions().get("en") != null) {
					entity.setDescription(entityDoc.getDescriptions().get("en").getText());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error fetching description from Wikidata for: " + entity.getText());
		}
	}

	// Simple class to represent named entities
	public static class NamedEntity {
		private String text;
		private String type;
		private String description;

		// Getters and setters
		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "NamedEntity{" + "text='" + text + '\'' + ", type='" + type + '\'' + ", description='" + description
					+ '\'' + '}';
		}
	}

	public static void main(String[] args) {
		// Example usage
		NerEntityExtractor extractor = new NerEntityExtractor();

		// Analyze sample text
		String sampleText = "Day 1-3: Rome, Italy \\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \"* Explore the Colosseum and Roman Forum\\n\"\n"
				+ "			+ \"* Visit the Vatican City and St. Peter's Basilica\\n\"\n"
				+ "			+ \"* Take a stroll along the Tiber River\\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \" Day 4-6: Madrid, Spain \\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \"* Visit the Royal Palace, Prado Museum, and Reina Sof?a Museum\\n\"\n"
				+ "			+ \"* Take a walk through the charming city center\\n\"\n"
				+ "			+ \"* Enjoy tapas and flamenco shows\\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \" Day 7-9: London, UK \\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \"* Visit Buckingham Palace, Tower of London, and the British Museum\\n\"\n"
				+ "			+ \"* Take a river cruise on the River Thames\\n\"\n"
				+ "			+ \"* Explore the charming neighborhoods of London\\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \" Day 10-12: Amsterdam, Netherlands \\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \"* Visit the Anne Frank House, Van Gogh , and Rijksmuseum\\n\"\n"
				+ "			+ \"* Take a canal cruise or bike ride through the city\\n\"\n"
				+ "			+ \"* Enjoy the vibrant nightlife and cafes\\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \" Day 13: Berlin, Germany \\n\"\n" + "			+ \"\\n\"\n"
				+ "			+ \"* Visit the Berlin Wall Memorial, Brandenburg Gate, and the East Side Gallery\\n\"\n"
				+ "			+ \"* Take a walk through the charming Tiergarten park\\n\"\n" + "			+ \"";
		
		String sampleText1 ="			+ \" Day 4-6: Madrid, Spain \\n\"\n" ;
		
		List<NamedEntity> entities = extractor.extractEntities(sampleText1);

		// Print out named entities with descriptions
		for (NamedEntity entity : entities) {
			if (entity.getType().equalsIgnoreCase("number"))
				System.out.println(entity.getText() + " --" + entity.getType());
			   System.out.println(entity.getDescription());

		}
	}
}
