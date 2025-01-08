package pdfcreation.java;



import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class FlightMethod {
	
	
	
	public static void flight() throws FileNotFoundException, MalformedURLException
	{
		
        String path1="/Users/krishnak/Documents/pdf/FlightMethod4.pdf";
        PdfWriter pdfWriter =new PdfWriter(path1);
        PdfDocument pdfDocument =new PdfDocument(pdfWriter);
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterPageEvent());

        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document= new Document(pdfDocument);
        
        String flight_right="image//flight-right.png";
        ImageData data7= ImageDataFactory.create(flight_right);
		Image flight_rightImg=new Image(data7);
		flight_rightImg.scaleAbsolute(20,20);
		
		String borderImg="image//borderImg.png";
        ImageData data8= ImageDataFactory.create(borderImg);
		Image borderImg1=new Image(data8);
		borderImg1.scaleAbsolute(300,20);
	    
		String flight1="image//spicejetIcon.png";
        ImageData data9= ImageDataFactory.create(flight1);
		Image flightImg1=new Image(data9);
		flightImg1.scaleAbsolute(250,20);
        
        document.add(new Paragraph("FLIGHT")
        		.setTextAlignment(TextAlignment.LEFT)
        		.setFontColor(new DeviceRgb(60, 179, 113))
				.setMarginTop(5f)
				.setMarginBottom(2f)
				.setMarginRight(50f)
				.setFontSize(18)
				.setBold()
        		);
        
        float ff[]= {10f,30f};
		Table ffTable= new Table(ff);
		
		ffTable.addCell(new Cell(2,0).add(flight_rightImg).setBorder(Border.NO_BORDER));
		ffTable.addCell(new Cell().add("Dubai").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		//ffTable.addCell(new Cell().add(" "));
		ffTable.addCell(new Cell().add("oneway").setBorder(Border.NO_BORDER).setFontSize(8));
		document.add(ffTable);
		
		
		
		document.add(new Paragraph("\n"));
		
		Border border= new SolidBorder(1);// Border  Design
		
		float flightImgList[]= {80f,300f,100f};
	    Table flightImgListTable = new Table(flightImgList);
	    flightImgListTable.setBorder(border);
	    Paragraph text = new Paragraph("krishna")
                .setBold()
                .setFontColor(Color.BLUE);
              // .setFont(PdfFontFactory.createFont(FontConstants.TIMES_ROMAN));

	    //one 
		flightImgListTable.addCell(new Cell().add("Economic").setBorder(Border.NO_BORDER).setFontSize(8));
		flightImgListTable.addCell(new Cell().add(flightImg1).add(text).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.CENTER));
		flightImgListTable.addCell(new Cell().add("NON Refundable").setBorder(Border.NO_BORDER).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
		//two
		flightImgListTable.addCell(new Cell().add("BLR").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		flightImgListTable.addCell(new Cell().add(borderImg1).setBorder(Border.NO_BORDER));
		flightImgListTable.addCell(new Cell().add("DXB").setBorder(Border.NO_BORDER).setFontSize(8).setBold().setTextAlignment(TextAlignment.RIGHT));
		
		//three
		flightImgListTable.addCell(new Cell().add("Bengaluru").setBorder(Border.NO_BORDER).setFontSize(8));
		flightImgListTable.addCell(new Cell().add("15h 15min").setBorder(Border.NO_BORDER).setFontSize(8).setFontColor(Color.BLACK).setTextAlignment(TextAlignment.CENTER));
		flightImgListTable.addCell(new Cell().add("Dubai").setBorder(Border.NO_BORDER).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
		
		//four
		flightImgListTable.addCell(new Cell().add("10:40 PM").setBorder(Border.NO_BORDER).setFontSize(8));
		flightImgListTable.addCell(new Cell().add("1 stop").setBorder(Border.NO_BORDER).setFontSize(8).setTextAlignment(TextAlignment.CENTER));
		flightImgListTable.addCell(new Cell().add("	10:40 PM").setBorder(Border.NO_BORDER).setFontSize(8).setTextAlignment(TextAlignment.RIGHT));
		
		//five
		flightImgListTable.addCell(new Cell().add("FLIGHT Number").setBorder(Border.NO_BORDER).setFontSize(9));
		flightImgListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		flightImgListTable.addCell(new Cell().add("Depature date & Time").setBorder(Border.NO_BORDER).setFontSize(9).setTextAlignment(TextAlignment.RIGHT));
		
		//six
		flightImgListTable.addCell(new Cell().add("SP 3604").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		flightImgListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		flightImgListTable.addCell(new Cell().add("SEP 16, 10:40 PM").setBorder(Border.NO_BORDER).setFontSize(9).setBold().setTextAlignment(TextAlignment.RIGHT));
		
		//seven
		flightImgListTable.addCell(new Cell(0,3).add("-----------------------------------------------------------------------------------------------------------------------").setBorder(Border.NO_BORDER));
		
		//eight
		flightImgListTable.addCell(new Cell(0,3).add("check in Bag @ bengalure").setBorder(Border.NO_BORDER).setFontSize(8).setFontColor(Color.BLACK));
		
		//nine
		flightImgListTable.addCell(new Cell(0,3).add("check in Bag @ bengalure").setBorder(Border.NO_BORDER).setFontSize(8).setFontColor(Color.BLACK));
		
		document.add(flightImgListTable);
		
        document.close();
		System.out.println("flight pdf is ready.........! ");
		//pdfDocument.saveToFile("/Users/krishnak/Downloads/krishna39.docx", FileFormat.DOCX);
	}

	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		// TODO Auto-generated method stub

	   flight();
	
	
	}

}

