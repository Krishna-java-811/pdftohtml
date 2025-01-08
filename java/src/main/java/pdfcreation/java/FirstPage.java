package pdfcreation.java;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class FirstPage {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		String path1 = "/Users/krishnak/Documents/pdf/documnt6.pdf";

		PdfWriter pdfWriter = new PdfWriter(path1);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.addEventHandler(PdfDocumentEvent.END_PAGE, new HeaderFooterPageEvent());
		pdfDocument.addNewPage();
		Document document = new Document(pdfDocument);
		pdfDocument.setDefaultPageSize(PageSize.A4);

		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));
		document.add(new Paragraph("\n"));

		String image = "image//1.PNG";
		ImageData data = ImageDataFactory.create(image);
		Image image1 = new Image(data);
		image1.scaleAbsolute(300, 150);

		String flight = "image//flight.png";
		ImageData data1 = ImageDataFactory.create(flight);
		Image flightImg = new Image(data1);

		String hotel = "image//hotels.png";
		ImageData data2 = ImageDataFactory.create(hotel);
		Image hotelImg = new Image(data2);

		String cab = "image//cabs.png";
		ImageData data3 = ImageDataFactory.create(cab);
		Image cabsImg = new Image(data3);

		String activity = "image//activity.png";
		ImageData data4 = ImageDataFactory.create(activity);
		Image activityImg = new Image(data4);

		String locate = "image//locate.png";
		ImageData data5 = ImageDataFactory.create(locate);
		Image locateImg = new Image(data5);
		locateImg.scaleAbsolute(30, 30);

		String circle = "image//circle-img.png";
		ImageData data6 = ImageDataFactory.create(circle);
		Image circleImg = new Image(data6);
		circleImg.scaleAbsolute(20, 20);

		float first[] = { 350F, 10F, 100f, 100f };
		Table table = new Table(first);

		table.addCell(new Cell(12, 0).add(image1).setBorder(Border.NO_BORDER));
		//table.addCell(Image.getInstance(new URL("https://travllr.imgix.net/Singapore/aaa.png?mask=ellipse&w=200&h=200")));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
//table.addCell(Image..getInstance(new URL("https://travllr.imgix.net/Singapore/aaa.png?mask=ellipse&w=200&h=200")));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add(" ").setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(flightImg).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add("Flight").setFontSize(11).setBorder(Border.NO_BORDER).setBold());
		table.addCell(new Cell().add("2000").setFontSize(11).setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(hotelImg).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add("Hotels").setFontSize(11).setBorder(Border.NO_BORDER).setBold());
		table.addCell(new Cell().add("2000").setFontSize(11).setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(cabsImg).setBorder(Border.NO_BORDER));
		table.addCell(new Cell().add("RentalCabs").setFontSize(11).setBorder(Border.NO_BORDER).setBold());
		table.addCell(new Cell().add("2000").setFontSize(11).setBorder(Border.NO_BORDER));

		table.addCell(new Cell().add(activityImg).setBorder(Border.NO_BORDER));
		table.addCell(
				new Cell().add("Activities + Self Exploration").setFontSize(11).setBorder(Border.NO_BORDER).setBold());
		table.addCell(new Cell().add("2000").setFontSize(11).setBorder(Border.NO_BORDER));
		document.add(table);

		document.add(new Paragraph("--------------------------------\n Total Cost including Tax \n 98,680.00 ")
				.setTextAlignment(TextAlignment.RIGHT).setMarginTop(5f).setMarginBottom(2f).setMarginRight(50f)
				.setFontSize(10));
		document.add(new Paragraph("As costed on 25/05/2023 at 17:13 \n Prices Subject to availability")
				.setTextAlignment(TextAlignment.RIGHT).setFontColor(new DeviceRgb(180, 180, 180)).setMarginTop(8f)
				.setMarginBottom(20f).setMarginRight(50f).setFontSize(8));
		document.add(new Paragraph(
				"The texes and fees component includes - all goverment taxes levied for your bookings pickyourtrail service ")
				.setTextAlignment(TextAlignment.CENTER).setMarginTop(9f).setMarginBottom(5f).setMarginRight(30f)
				.setFontSize(10));
		AreaBreak nextPage = new AreaBreak();
		document.add(nextPage);

		document.add(new Paragraph("\n"));
		document.add(new Paragraph("Itinerary").setTextAlignment(TextAlignment.LEFT)
				.setFontColor(new DeviceRgb(60, 179, 113)).setMarginTop(5f).setMarginBottom(2f).setMarginRight(50f)
				.setFontSize(18).setBold());

		float location[] = { 10f, 50f };
		Table locationTable = new Table(location);
		locationTable.addCell(new Cell(2, 0).add(locateImg).setBorder(Border.NO_BORDER));
		locationTable.addCell(new Cell().add("Dubai").setBold().setFontSize(18).setFontColor(Color.BLACK)
				.setBorder(Border.NO_BORDER));
		locationTable.addCell(
				new Cell().add("3 Nights 4 Days").setFontSize(9).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER));
		document.add(locationTable);

		document.add(new Paragraph("\n"));

		float plan[] = { 50f, 80f, 300f };
		Table planTable = new Table(plan);
		planTable.addCell(new Cell(3, 0).add("14 Dec").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add("FULLDAY").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add("overnight  in london ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell(4, 0).add("15 Dec").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add("Morning").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("NOON").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable.addCell(new Cell().add("Lunch at restaurant ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("Evening").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add(circleImg).setBorder(Border.NO_BORDER));

		planTable.addCell(new Cell(4, 0).add("16 Dec").setBorder(Border.NO_BORDER));
		planTable.addCell(new Cell().add("Morning").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("NOON").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable.addCell(new Cell().add("Lunch at restaurant ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("Evening").setBorder(Border.NO_BORDER).setFontSize(9).setBold());
		planTable.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		byte[] response = urlImagetoByte("https://travllr.imgix.net/Dubai/Activity/WildWadiWaterpark1.png?mask=ellipse&w=200&h=200");
		planTable.addCell(new Cell().add(new Image(ImageDataFactory.create(response))).setBorder(Border.NO_BORDER));
		//planTable.addCell(new Cell().add(circleImg).setBorder(Border.NO_BORDER));

		document.add(planTable);
		document.add(new Paragraph("\n"));

		float location1[] = { 10f, 50f };
		Table locationTable1 = new Table(location1);
		locationTable1.addCell(new Cell(2, 0).add(locateImg).setBorder(Border.NO_BORDER));
		locationTable1.addCell(new Cell().add("Saudi").setBold().setFontSize(18).setFontColor(Color.BLACK)
				.setBorder(Border.NO_BORDER));
		locationTable1.addCell(
				new Cell().add("3 Nights 4 Days").setFontSize(9).setFontColor(Color.BLACK).setBorder(Border.NO_BORDER));
		document.add(locationTable1);

		document.add(new Paragraph("\n"));
		float plan1[] = { 50f, 80f, 300f };
		Table planTable1 = new Table(plan1);
		planTable1.addCell(new Cell(3, 0).add("14 Dec").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add("FULLDAY").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add("overnight  in london ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell(4, 0).add("15 Dec").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add("Morning").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("NOON").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1.addCell(new Cell().add("Lunch at restaurant ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("Evening").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell(0, 1).add("").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add(circleImg).setBorder(Border.NO_BORDER));

		planTable1.addCell(new Cell(4, 0).add("16 Dec").setBorder(Border.NO_BORDER));
		planTable1.addCell(new Cell().add("Morning").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1
				.addCell(new Cell().add("on arrive in london,clear the immigration and proceed to the hotel in london.")
						.setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("NOON").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1.addCell(new Cell().add("Lunch at restaurant ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell().add("Evening").setBorder(Border.NO_BORDER).setBold().setFontSize(9));
		planTable1.addCell(new Cell().add("Dinner at the hotel. ").setBorder(Border.NO_BORDER).setFontSize(10));

		planTable1.addCell(new Cell(0, 1).add("").setBorder(Border.NO_BORDER));
		byte[] responses = urlImagetoByte("https://travllr.imgix.net/Dubai/Activity/burj1.png");
		planTable1.addCell(new Cell().add(new Image(ImageDataFactory.create(responses))).setBorder(Border.NO_BORDER));
		//planTable1.addCell(new Cell().add(circleImg).setBorder(Border.NO_BORDER));
		document.add(planTable1);

		document.close();
		System.out.println("okk");
	}
	private static byte[] urlImagetoByte(String urls) throws MalformedURLException, IOException {
		URL url = new URL(urls); 	
        InputStream is =url.openStream();
        InputStream in = new BufferedInputStream(url.openStream()); 	
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



}


//class HeaderFooterPageEvent extends PdfPageEventHelper {
//
//	public void onStartPage(com.itextpdf.text.pdf.PdfWriter writer, Document document) {
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Left"), 30, 800, 0);
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, new Phrase("Top Right"), 550, 800,
//				0);
//	}
//
//	public void onEndPage(com.itextpdf.text.pdf.PdfWriter writer, Document document) {
//		try {
//
//			com.itextpdf.text.Image image = com.itextpdf.text.Image
//					.getInstance(getClass().getClassLoader().getResource("2.PNG"));
//
//			PdfPTable righttable = new PdfPTable(3);
//			righttable.setWidthPercentage(100);
//			righttable.setTotalWidth(PageSize.A4.getWidth() - 70);
//			// righttable.(document.leftMargin(), document.bottomMargin(),
//			// document.getPageSize().getWidth() - document.leftMargin() -
//			// document.rightMargin());
//
//			righttable.setWidths(new float[] { 50, 25, 25 });
//			righttable.getDefaultCell().setBorder(0);
//			righttable.addCell(image);
//			righttable.addCell(new Phrase("=91 98"));
//			righttable.setTotalWidth(527);
//			righttable.setLockedWidth(true);
//			righttable.getDefaultCell().setBorder(Rectangle.TOP);
//			righttable.getDefaultCell().setBorderColor(BaseColor.LIGHT_GRAY);
//			righttable.getDefaultCell().setFixedHeight(40);
//			righttable.addCell("ramalingaguru.g@gmail.com");
//			PdfContentByte canvas = writer.getDirectContent();
//			canvas.beginMarkedContentSequence(com.itextpdf.text.pdf.PdfName.ARTIFACT);
//			righttable.writeSelectedRows(0, -1, 34, 120, canvas);
//			canvas.endMarkedContentSequence();
//			// document.add(lefttable);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//}
