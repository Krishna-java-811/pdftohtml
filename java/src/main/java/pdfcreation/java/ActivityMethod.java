package pdfcreation.java;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class ActivityMethod {

	
	public static void activity() throws FileNotFoundException, MalformedURLException
	{
		String path1="/Users/krishnak/Documents/acitivity.pdf";
        PdfWriter pdfWriter =new PdfWriter(path1);
        PdfDocument pdfDocument =new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterPageEvent());

        Document document= new Document(pdfDocument);
        
        String activityIcon="image//activityIcon.png";
		ImageData data14= ImageDataFactory.create(activityIcon);
		Image activityIconImg=new Image(data14);
		activityIconImg.scaleAbsolute(20, 20);
        
		String activityImg="image//activityImg.png";
		ImageData data15= ImageDataFactory.create(activityImg);
		Image activityImgImg=new Image(data15);
		activityImgImg.scaleAbsolute(100, 50);
        
        
        document.add(new Paragraph("Activities")
        		.setTextAlignment(TextAlignment.LEFT)
        		.setFontColor(new DeviceRgb(60, 179, 113))
				.setMarginTop(5f)
				.setMarginBottom(2f)
				.setMarginRight(50f)
				.setFontSize(18)
				.setBold()
        		);
		
		document.add(new Paragraph("\n"));
		
		float activity[]= {10f,300f,100f};
		Table activityTable= new Table(activity);
		
		//1
		activityTable.addCell(new Cell(2,0).add(activityIconImg).setBorder(Border.NO_BORDER));
		activityTable.addCell(new Cell().add("Dubai Aquarium, Dubai Frame & Other  Places").setBorder(Border.NO_BORDER).setFontSize(11));
		activityTable.addCell(new Cell().add("NON REFUNDABLE").setBorder(Border.NO_BORDER).setFontSize(8).setTextAlignment(TextAlignment.RIGHT).setFontColor(Color.RED));
		//2
		activityTable.addCell(new Cell().add("Morning Activity |  Transfer Included").setBorder(Border.NO_BORDER).setFontSize(9));
		activityTable.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER).setFontSize(7));
		document.add(activityTable);
		
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		
		float activityDes[]= {50f,200f,100f,100f,100f};
		Table activityDesTable= new Table(activityDes);
		
		//1
		activityDesTable.addCell(new Cell(3,0).add(activityImgImg).setBorder(Border.NO_BORDER));
		activityDesTable.addCell(new Cell().add("Date & Time").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
		activityDesTable.addCell(new Cell().add("Duration").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
		activityDesTable.addCell(new Cell().add("Hotel Pickup").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
		activityDesTable.addCell(new Cell().add("city").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
		
		//2
		activityDesTable.addCell(new Cell().add("29 oct 2021").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		activityDesTable.addCell(new Cell().add("3hrs 30mins").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		activityDesTable.addCell(new Cell().add("yes").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		activityDesTable.addCell(new Cell().add("Al Ain").setBorder(Border.NO_BORDER).setFontSize(8).setBold());
		
		//3
		activityDesTable.addCell(new Cell(0,4).add("Dubai Aquarium, Dubai Frame & Other  Places. Dubai Aquarium, Dubai Frame & Other  Places.Dubai Aquarium, Dubai Frame & Other  Places.Dubai Aquarium, Dubai Frame & Other  Places").setBorder(Border.NO_BORDER).setFontSize(8));
		
		
		//4
//		activityDesTable.addCell(new Cell(0,2).add("Inclusion").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
//		activityDesTable.addCell(new Cell(0,2).add("Exclusion").setBorder(Border.NO_BORDER).setFontSize(10).setBold());
//		
//		
//		//5
//		activityDesTable.addCell(new Cell(0,2).add("Dubai Frame ").setBorder(Border.NO_BORDER).setFontSize(10));
//		activityDesTable.addCell(new Cell(0,2).add("Other  Places").setBorder(Border.NO_BORDER).setFontSize(10));
//		
//		
//		//6
//		activityDesTable.addCell(new Cell(0,2).add("Dubai Frame").setBorder(Border.NO_BORDER).setFontSize(10));
//		activityDesTable.addCell(new Cell(0,2).add("Other  Places").setBorder(Border.NO_BORDER).setFontSize(10));
//		
//		
//		//7
//		activityDesTable.addCell(new Cell(0,2).add("Other  Places").setBorder(Border.NO_BORDER).setFontSize(10));
//		activityDesTable.addCell(new Cell(0,2).add("Dubai Frame").setBorder(Border.NO_BORDER).setFontSize(10));
//		
		
		document.add(activityDesTable);
		
		document.close();
		System.out.println("Activity pdf ..... !");
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		// TODO Auto-generated method stub

	 activity();
	
	
	}

}

