package NER;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class PDFSplitterWithNER {
    public static void main(String[] args) {
        // Paths
        String inputPdf = "/Users/krishnak/Downloads/A2 Europe Summer_2025 v1-1.pdf";
        String outputDir = "/Users/krishnak/Documents/SplitPDF/";

        // Define the target headings/entities to split by
        List<String> targetHeadings = Arrays.asList("DAY", "INCLUSIONS", "HOTELS", "COST AND POLICY", "VISA");

        try {
            // Load the PDF
            PDDocument document = PDDocument.load(new File(inputPdf));
            PDFTextStripper textStripper = new PDFTextStripper();
            textStripper.setSortByPosition(true);

            // Extract text from PDF
            String pdfText = textStripper.getText(document);
            System.out.println("Extracted Text: \n" + pdfText); // Debugging

            // Use Stanford NER to detect headings
            Map<Integer, String> splitPoints = getSplitPointsUsingNER(pdfText, targetHeadings);

            System.out.println("Split Points: " + splitPoints); // Debugging

            // Split the PDF based on detected sections
            if (splitPoints.isEmpty()) {
                System.out.println("No split points detected. Please check the headings or PDF content.");
            } else {
                splitPDF(document, splitPoints, outputDir);
            }

            document.close();
            System.out.println("PDF split successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, String> getSplitPointsUsingNER(String text, List<String> targetHeadings) {
        // Set up the Stanford NLP pipeline
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma,ner");
     // Specify the path to the model files
     		props.setProperty("pos.model", "/Users/krishnak/Documents/models_jarfiles/english-left3words-distsim.tagger");
     		props.setProperty("ner.model",
     				"/Users/krishnak/Documents/models_jarfiles/english.muc.7class.distsim.crf.ser.gz");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Process the text with Stanford NER
        Annotation document = new Annotation(text);
        pipeline.annotate(document);

        // Extract sentences and look for target headings
        Map<Integer, String> splitPoints = new HashMap<>();
        int pageIndex = 1;

        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            String sentenceText = sentence.toString();

            for (String heading : targetHeadings) {
                if (sentenceText.toUpperCase().contains(heading.toUpperCase())) {
                    splitPoints.put(pageIndex, heading);
                }
            }

            // Assume that a page roughly contains around 1000 characters (adjust as needed)
            if (sentenceText.length() > 1000) {
                pageIndex++;
            }
        }

        return splitPoints;
    }

    private static void splitPDF(PDDocument document, Map<Integer, String> splitPoints, String outputDir) throws IOException {
        int startPage = 1;
        String currentHeading = null;

        for (Map.Entry<Integer, String> entry : splitPoints.entrySet()) {
            int endPage = entry.getKey() - 1;

            if (currentHeading != null) {
                saveSplit(document, startPage, endPage, outputDir + currentHeading + ".pdf");
            }

            startPage = entry.getKey();
            currentHeading = entry.getValue();
        }

        if (currentHeading != null) {
            saveSplit(document, startPage, document.getNumberOfPages(), outputDir + currentHeading + ".pdf");
        }
    }

    private static void saveSplit(PDDocument document, int startPage, int endPage, String outputFile) throws IOException {
        PDDocument splitDoc = new PDDocument();
        for (int i = startPage; i <= endPage; i++) {
            splitDoc.addPage(document.getPage(i - 1));
        }
        splitDoc.save(outputFile);
        splitDoc.close();
    }
}
