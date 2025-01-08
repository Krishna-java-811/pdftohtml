package pdf.convert.html.html.convert;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.fit.pdfdom.PDFDomTree;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;



public class PdfToHtmlSplitter {

    private static final String PDF_FILE_PATH = "/Users/krishnak/Downloads/ilovepdf_compressed/A18_Travllr_Australia_Summer_2025_v1.pdf";
    private static final String OUTPUT_DIR = "/Users/krishnak/Downloads/ilovepdf_compressed/splithtml/";

    public static void main(String[] args) throws Exception {
        File outputDirectory = new File(OUTPUT_DIR);
        if (!outputDirectory.exists()) {
            outputDirectory.mkdirs();
        }

        try (PDDocument document = PDDocument.load(new File(PDF_FILE_PATH))) {
            for (int pageNum = 0; pageNum < document.getNumberOfPages(); pageNum++) {
                processPage(document, pageNum);
            }
            System.out.println("PDF split into individual HTML files successfully!");
        } catch (IOException e) {
            System.err.println("Error while processing the PDF: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void processPage(PDDocument document, int pageNum) throws Exception {
        // Create a new document with a single page
        try (PDDocument singlePageDoc = new PDDocument()) {
            PDPage currentPage = document.getPage(pageNum);
            singlePageDoc.addPage(currentPage);

            // Output HTML file name
            String outputFileName = OUTPUT_DIR + "page_" + (pageNum + 1) + ".html";
            generateHTMLFromPDF(singlePageDoc, outputFileName);

            // Apply styles and clean up HTML
            cleanUpHtml(outputFileName);

            System.out.println("Saved: " + outputFileName);
        }
    }

    private static void generateHTMLFromPDF(PDDocument singlePageDocument, String outputHtmlFile) throws IOException, Exception {
        PDFDomTree parser = new PDFDomTree();
        try (PrintWriter output = new PrintWriter(outputHtmlFile, "UTF-8")) {
            parser.writeText(singlePageDocument, output);
        }
    }

    private static void cleanUpHtml(String htmlFilePath) throws IOException {
        File inputFile = new File(htmlFilePath);
        Document doc = Jsoup.parse(inputFile, "UTF-8");

        applyStyles(doc);
        removeBorders(doc);

        try (PrintWriter output = new PrintWriter(htmlFilePath, "UTF-8")) {
            output.write(doc.html());
        }
    }

    private static void applyStyles(Document doc) {
        // Apply styles to all page elements
        Elements pages = doc.select(".page");
        pages.forEach(page -> page.attr("style", "left: -45.0pt; top: -100.0pt; width: 575.0pt; height: 680.0pt; overflow: hidden; padding: 0; position: relative !important;"));
    }

    private static void removeBorders(Document doc) {
        // Remove borders from .page elements
        doc.select(".page").forEach(element -> {
            String style = element.attr("style");
            if (style != null && style.contains("border")) {
                element.attr("style", style.replaceAll("border:\\s*[^;]+", "border: none"));
            }
        });

        // Remove border-related CSS from <style> tags
        doc.select("style").forEach(styleTag -> {
            String cssContent = styleTag.html().replaceAll("border:\\s*[^;]+", "border: none");
            styleTag.html(cssContent);
        });
    }
}
