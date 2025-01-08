package pdfcreation.java;



import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.DeviceRgb;
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

public class HotelMethod {
	
	public static void hotel() throws FileNotFoundException, MalformedURLException
	{
		String path1="/Users/krishnak/Documents/travellr Web/HotelMethod2.pdf";
        PdfWriter pdfWriter =new PdfWriter(path1);
        PdfDocument pdfDocument =new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
        Document document= new Document(pdfDocument);
		
          // hotel, star, adviser, hotel Icon
        
		 String MainHotel="image//mainHotel1.png";
		 ImageData data10= ImageDataFactory.create(MainHotel);
		 Image MainHotelImg=new Image(data10);
		 MainHotelImg.scaleAbsolute(100, 80);
		 
		 String Star="image//star.png";
		 ImageData data12= ImageDataFactory.create(Star);
		 Image StarImg=new Image(data12);
		 StarImg.scaleAbsolute(60, 35);
		 
		 String Adviser="image//adviser.png";
		 ImageData data11= ImageDataFactory.create(Adviser);
		 Image AdviserImg=new Image(data11);
		 AdviserImg.scaleAbsolute(20, 20);
		 
		 String hotelIcon="image//hotel-pdf.png";
		 ImageData data13= ImageDataFactory.create(hotelIcon);
		 Image hotelIconImg=new Image(data13);
		 hotelIconImg.scaleAbsolute(20, 20);
		 
        
        document.add(new Paragraph("HOTELS")
        		.setTextAlignment(TextAlignment.LEFT)
        		.setFontColor(new DeviceRgb(60, 179, 113))
				.setMarginTop(5f)
				.setMarginBottom(2f)
				.setMarginRight(50f)
				.setFontSize(18)
				.setBold()
        		);
		
		document.add(new Paragraph("\n"));
		
		
		float hotel1[]= {10f,30f};
		Table ffTable1= new Table(hotel1);
		ffTable1.addCell(new Cell(2,0).add(hotelIconImg).setBorder(Border.NO_BORDER));
		ffTable1.addCell(new Cell().add("Dubai").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		ffTable1.addCell(new Cell().add("3 Nights").setBorder(Border.NO_BORDER).setFontSize(7));
		document.add(ffTable1);
		
		Border border= new SolidBorder(1);// Border  Design
		
		document.add(new Paragraph("\n"));
		float hotelList[]= {80f,150f,150f,100f};
		Table hotelListTable= new Table(hotelList);
		hotelListTable.setBorder(border);
		
		//one
		hotelListTable.addCell(new Cell().add("Hotel Name ").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
		hotelListTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add("Non Refundable ").setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT).setFontSize(8).setBold());
		
		// 2
		hotelListTable.addCell(new Cell().add("Day 1-4").setBorder(Border.NO_BORDER).setFontSize(7).setBold());
		hotelListTable.addCell(new Cell().add("Check-In ").setBorder(Border.NO_BORDER).setFontSize(7));
		hotelListTable.addCell(new Cell().add("Check-Out ").setBorder(Border.NO_BORDER).setFontSize(7));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		
		// 3
		hotelListTable.addCell(new Cell().add("Dubai ").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		hotelListTable.addCell(new Cell().add("23-Oct-2021 Tue 11:11am ").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		hotelListTable.addCell(new Cell().add("23-Oct-2021 Fri 04:30pm ").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		
		// 4
		hotelListTable.addCell(new Cell(4,0).add(MainHotelImg).setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add("Hotel Name").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		
		// 5
		hotelListTable.addCell(new Cell().add(StarImg).setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		
		// 6
	    hotelListTable.addCell(new Cell().add(AdviserImg).setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add("  ").setBorder(Border.NO_BORDER));
		
		// 7
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		hotelListTable.addCell(new Cell().add("  ").setBorder(Border.NO_BORDER));
		document.add(hotelListTable);
		document.close();
		
		System.out.println("hotel pdf is ready.........! ");
	}
	
	

	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		
		
		hotel();

	}

}

