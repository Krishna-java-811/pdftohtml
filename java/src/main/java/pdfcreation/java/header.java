package pdfcreation.java;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;


class HeaderFooterPageEvent implements IEventHandler{  
		 private   byte[] urlImagetoByte(String urls) throws MalformedURLException, IOException {
			URL url = new URL(urls); 	
	        java.io.InputStream is =url.openStream();
	        java.io.InputStream in = new BufferedInputStream(url.openStream()); 	
	        int c1 = is.read();
	        ByteArrayOutputStream out = new ByteArrayOutputStream(); 		
	        byte[] buf = new byte[1024]; 				int n = 0; 			
	        while (-1!=(n=in.read(buf)))  { 	
	        	out.write(buf, 0, n); 			
	        	} 				out.close(); 	
	        	in.close(); 			
	        	byte[] response = out.toByteArray();
			return response;
		}
		@Override
		public void handleEvent(Event event) {
			   try {
		          float side = 20;
		          float x = 300;
		          float y = 25;
		          float space = 4.5f;
		          float descent = 3;
		          PdfFormXObject placeholder =new PdfFormXObject(new Rectangle(0, 0, side, side));
			PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	        PdfDocument pdf = docEvent.getDocument();
	        PdfPage page = docEvent.getPage();
	        int pageNumber = pdf.getPageNumber(page);
	        Rectangle pageSize = page.getPageSize();
	        PdfCanvas pdfCanvas = new PdfCanvas(
	                page.getLastContentStream(), page.getResources(), pdf);
	        pdfCanvas.addImage((ImageDataFactory.create("image//logo.png")), 50, y - descent, 150,false);
	      
	        Canvas canvas = new Canvas(pdfCanvas, pdf, pageSize);
	        Paragraph p = new Paragraph()
	                .add("+91 ").add(String.valueOf(pageNumber)).add(" of");
	        Paragraph p1 = new Paragraph()
	                .add("+91 ").add(String.valueOf(pageNumber)).add(" of");
	       canvas.showTextAligned(p, x, y,TextAlignment.RIGHT);
	       canvas.showTextAligned(p1, 450, y,TextAlignment.RIGHT);
	        canvas.add(new Image(ImageDataFactory.create("image//logo.png")));
	        canvas.setFixedPosition(x + space, y - descent, 35);
	       // pdfCanvas.addXObject(placeholder, x + space, y - descent);
	       // pdfCanvas.release();
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
		}

	}

