package pdf.convert.html.html.convert;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;

public class PDF2HTMLExample {

    private static final String PDF = "/Users/krishnak/Downloads/ilovepdf_compressed/A17_New_Zealand_Summer_2025_v1.pdf";
    private static final String OUTPUT_HTML = "/Users/krishnak/Downloads/ilovepdf_compressed/pdf1.html";
    
    public static void main(String[] args) throws Exception {
        try {
            // Step 1: Generate HTML from PDF
            generateHTMLFromPDF(PDF);
            
       
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }

    // Method to convert PDF to HTML
    private static void generateHTMLFromPDF(String filename) throws Exception, IOException {
        PDDocument pdf = PDDocument.load(new File(filename));
        PDFDomTree parser = new PDFDomTree();
        
        // Write the PDF content to the output HTML file
        Writer output = new PrintWriter(OUTPUT_HTML, "utf-8");
        parser.writeText(pdf, output);
        output.close();
        
        if (pdf != null) {
            pdf.close();
        }
    }

 
    
}
